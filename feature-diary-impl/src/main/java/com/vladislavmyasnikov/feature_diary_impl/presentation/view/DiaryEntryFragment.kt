package com.vladislavmyasnikov.feature_diary_impl.presentation.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vladislavmyasnikov.core_components.interfaces.ScreenTitleController
import com.vladislavmyasnikov.core_components.view.DatePickerFragment
import com.vladislavmyasnikov.core_components.view.TimePickerFragment
import com.vladislavmyasnikov.core_utils.utils.utils.DayMonthYearFormat
import com.vladislavmyasnikov.core_utils.utils.utils.HourMinuteFormat
import com.vladislavmyasnikov.core_utils.utils.utils.Logger
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.di.DiaryFeatureComponent
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.DiaryEntryViewModel
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.DiaryViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_diary_entry.*
import javax.inject.Inject

class DiaryEntryFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: DiaryViewModelFactory

    @Inject
    lateinit var screenTitleController: ScreenTitleController

    private lateinit var diaryVM: DiaryEntryViewModel
    private val disposables = CompositeDisposable()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        DiaryFeatureComponent.get().inject(this)
        diaryVM = ViewModelProviders.of(this, viewModelFactory).get(DiaryEntryViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_diary_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        screenTitleController.setTitle(arguments!!.getString(TITLE_ARG)!!)
        screenTitleController.setDisplayHomeAsUpEnabled(true)

        date_button.setOnClickListener {
            val dialog = DatePickerFragment.newInstance(diaryVM.entry.date)
            dialog.setTargetFragment(this, GET_DATE_REQUEST_CODE)
            dialog.show(fragmentManager, DATE_PICKER_DIALOG_TAG)
        }

        start_time_button.setOnClickListener {
            val dialog = TimePickerFragment.newInstance(diaryVM.entry.startTime)
            dialog.setTargetFragment(this, GET_START_TIME_REQUEST_CODE)
            dialog.show(fragmentManager, TIME_PICKER_DIALOG_TAG)
        }

        end_time_button.setOnClickListener {
            val dialog = TimePickerFragment.newInstance(diaryVM.entry.endTime)
            dialog.setTargetFragment(this, GET_END_TIME_REQUEST_CODE)
            dialog.show(fragmentManager, TIME_PICKER_DIALOG_TAG)
        }

        disposables.add(diaryVM.loadingState
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Logger.debug(TAG, "Entry fetching: LOADING IS ${if (it) "STARTED" else "COMPLETED"}")
                })

        disposables.add(diaryVM.errors
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Logger.debug(TAG, "Entry fetching: ERROR; Cause: $it")
                })

        disposables.add(diaryVM.items
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Logger.debug(TAG, "Entry fetching: SUCCESS")
                    updateContent()
                    //TODO: how to use emitted item?
                })

        if (savedInstanceState == null) {
            Logger.debug(TAG, "Entry fetching: REQUEST")
            diaryVM.fetchFullEntry(arguments!!.getLong(DIARY_ENTRY_ID_ARG))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            data?.let {
                when (requestCode) {
                    GET_DATE_REQUEST_CODE -> {
                        val date = DatePickerFragment.extractDate(data)
                        diaryVM.entry.date = date
                        updateDate()
                    }
                    GET_START_TIME_REQUEST_CODE -> {
                        val time = TimePickerFragment.extractTime(data)
                        diaryVM.entry.startTime = time
                        updateTime()
                    }
                    GET_END_TIME_REQUEST_CODE -> {
                        val time = TimePickerFragment.extractTime(data)
                        diaryVM.entry.endTime = time
                        updateTime()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun updateContent() {
        updateDate()
        updateTime()
    }

    private fun updateDate() {
        date_field.text = DayMonthYearFormat.format(diaryVM.entry.date)
    }

    private fun updateTime() {
        val s = "${HourMinuteFormat.format(diaryVM.entry.startTime)} ― ${HourMinuteFormat.format(diaryVM.entry.endTime)}"
        time_field.text = s
    }

    companion object {
        private const val TAG = "DIARY_ENTRY_FRAGMENT"
        private const val DIARY_ENTRY_ID_ARG = "diary_entry_id_arg"
        private const val TITLE_ARG = "title_arg"
        private const val DATE_PICKER_DIALOG_TAG = "date_picker_dialog"
        private const val TIME_PICKER_DIALOG_TAG = "time_picker_dialog"
        private const val GET_DATE_REQUEST_CODE = 1
        private const val GET_START_TIME_REQUEST_CODE = 2
        private const val GET_END_TIME_REQUEST_CODE = 3

        fun newInstance(id: Long, title: String): DiaryEntryFragment {
            return DiaryEntryFragment().apply {
                arguments = Bundle().apply {
                    putLong(DIARY_ENTRY_ID_ARG, id)
                    putString(TITLE_ARG, title)
                }
            }
        }
    }
}