package com.vladislavmyasnikov.feature_diary_impl.presentation.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.vladislavmyasnikov.core_components.components.DateFormatter
import com.vladislavmyasnikov.core_components.components.GeneralViewModel
import com.vladislavmyasnikov.core_components.components.TimePointFormatter
import com.vladislavmyasnikov.core_components.interfaces.OnBackPressedListener
import com.vladislavmyasnikov.core_components.models.TimePoint
import com.vladislavmyasnikov.core_components.view.DatePickerFragment
import com.vladislavmyasnikov.core_components.view.GeneralFragment
import com.vladislavmyasnikov.core_components.view.TimePickerFragment
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.di.DiaryFeatureComponent
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.DiaryEntryViewModel
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_diary_entry.*
import javax.inject.Inject

class DiaryEntryFragment : GeneralFragment<DiaryEntryViewModel>(), OnBackPressedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var lastButtonClickTime = -1000L

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        DiaryFeatureComponent.get().inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DiaryEntryViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_diary_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        screenTitleController.setTitle(arguments!!.getString(TITLE_ARG)!!)
        screenTitleController.setDisplayHomeAsUpEnabled(true)

        date_button.setOnClickListener {
            if (SystemClock.elapsedRealtime() - lastButtonClickTime < 1000) return@setOnClickListener
            lastButtonClickTime = SystemClock.elapsedRealtime()
            val dialog = DatePickerFragment.newInstance(viewModel.entry.date)
            dialog.setTargetFragment(this, GET_DATE_REQUEST_CODE)
            dialog.show(fragmentManager, DATE_PICKER_DIALOG_TAG)
        }

        start_time_button.setOnClickListener {
            if (SystemClock.elapsedRealtime() - lastButtonClickTime < 1000) return@setOnClickListener
            lastButtonClickTime = SystemClock.elapsedRealtime()
            val dialog = TimePickerFragment.newInstance(viewModel.entry.startTime)
            dialog.setTargetFragment(this, GET_START_TIME_REQUEST_CODE)
            dialog.show(fragmentManager, TIME_PICKER_DIALOG_TAG)
        }

        end_time_button.setOnClickListener {
            if (SystemClock.elapsedRealtime() - lastButtonClickTime < 1000) return@setOnClickListener
            lastButtonClickTime = SystemClock.elapsedRealtime()
            val dialog = TimePickerFragment.newInstance(viewModel.entry.endTime)
            dialog.setTargetFragment(this, GET_END_TIME_REQUEST_CODE)
            dialog.show(fragmentManager, TIME_PICKER_DIALOG_TAG)
        }

        save_button.setOnClickListener {
            save_button.isEnabled = false
            viewModel.saveFullEntry()
        }

        if (savedInstanceState == null) {
            viewModel.fetchFullEntry(arguments!!.getLong(DIARY_ENTRY_ID_ARG))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            GET_DATE_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val date = DatePickerFragment.extractDate(data!!)
                    viewModel.entry.date = date
                    updateDateField()
                }
            }
            GET_START_TIME_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val time = TimePickerFragment.extractTime(data!!)
                    checkAndUpdateTime(time, START_TIME_TYPE)
                }
            }
            GET_END_TIME_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val time = TimePickerFragment.extractTime(data!!)
                    checkAndUpdateTime(time, END_TIME_TYPE)
                }
            }
        }
    }

    override fun <Int> onReceiveItem(item: Int) {
        when (item) {
            GeneralViewModel.LOADED_REQUEST_RESULT -> { updateContent() }
            GeneralViewModel.SAVED_REQUEST_RESULT -> { activity?.onBackPressed() }
        }
    }

    override fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    private fun updateContent() {
        updateDateField()
        updateTimeField()
        updateDurationField()
    }

    private fun updateDateField() {
        date_field.text = DateFormatter.format(viewModel.entry.date, DateFormatter.DAY_MONTH_YEAR_FORMAT)
    }

    private fun updateTimeField() {
        time_field.text = TimePointFormatter.formatInterval(viewModel.entry.startTime, viewModel.entry.endTime, TimePointFormatter.HOUR_MINUTE_FORMAT)
    }

    private fun updateDurationField() {
        duration_field.text = TimePointFormatter.format(viewModel.entry.duration, TimePointFormatter.HOUR_MINUTE_FORMAT)
    }

    private fun checkAndUpdateTime(time: TimePoint, type: Int) {
        if (isTimePermissible(time, type)) {
            when (type) {
                START_TIME_TYPE -> viewModel.entry.startTime = time
                END_TIME_TYPE -> viewModel.entry.endTime = time
            }
            viewModel.entry.duration = TimePoint(viewModel.entry.endTime.toMilliseconds() - viewModel.entry.startTime.toMilliseconds())
            updateTimeField()
            updateDurationField()
        } else {
            Toast.makeText(activity, R.string.not_correct_time_input_message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun isTimePermissible(time: TimePoint, type: Int): Boolean {
        return when (type) {
            START_TIME_TYPE -> time.before(viewModel.entry.endTime)
            END_TIME_TYPE -> time.after(viewModel.entry.startTime)
            else -> false
        }
    }

    companion object {
        private const val DIARY_ENTRY_ID_ARG = "diary_entry_id_arg"
        private const val TITLE_ARG = "title_arg"
        private const val DATE_PICKER_DIALOG_TAG = "date_picker_dialog"
        private const val TIME_PICKER_DIALOG_TAG = "time_picker_dialog"
        private const val GET_DATE_REQUEST_CODE = 1
        private const val GET_START_TIME_REQUEST_CODE = 2
        private const val GET_END_TIME_REQUEST_CODE = 3
        private const val START_TIME_TYPE = 1
        private const val END_TIME_TYPE = 2

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