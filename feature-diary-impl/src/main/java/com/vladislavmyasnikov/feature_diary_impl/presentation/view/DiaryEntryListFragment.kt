package com.vladislavmyasnikov.feature_diary_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.core_components.interfaces.FragmentController
import com.vladislavmyasnikov.core_utils.utils.utils.Logger
import com.vladislavmyasnikov.core_components.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.core_components.interfaces.ScreenTitleController
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.di.DiaryFeatureComponent
import com.vladislavmyasnikov.feature_diary_impl.presentation.adapters.ShortDiaryEntryAdapter
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.DiaryEntryListViewModel
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.DiaryEntryListViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DiaryEntryListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: DiaryEntryListViewModelFactory

    @Inject
    lateinit var adapter: ShortDiaryEntryAdapter

    @Inject
    lateinit var screenTitleController: ScreenTitleController

    @Inject
    lateinit var fragmentController: FragmentController

    private lateinit var diaryVM: DiaryEntryListViewModel
    private val disposables = CompositeDisposable()

    private val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, name: String) { }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        DiaryFeatureComponent.get().inject(this)
        diaryVM = ViewModelProviders.of(this, viewModelFactory).get(DiaryEntryListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        screenTitleController.setTitle("Diary")
        screenTitleController.setDisplayHomeAsUpEnabled(false)

        adapter.callback = itemClickCallback
        view.findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter

        disposables.add(diaryVM.loadingState
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Logger.debug(TAG, "Entries fetching: LOADING IS ${if (it) "STARTED" else "COMPLETED"}")
                })

        disposables.add(diaryVM.errors
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Logger.debug(TAG, "Entries fetching: ERROR; Cause: $it")
                })

        disposables.add(diaryVM.items
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Logger.debug(TAG, "Entries fetching: SUCCESS; Amount: ${it.size}")
                    adapter.updateList(it)
                })

        if (savedInstanceState == null) {
            Logger.debug(TAG, "Entries fetching: REQUEST")
            diaryVM.fetchShortEntries()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    companion object {
        private const val TAG = "DIARY_ENTRY_LIST_FRAGMENT"

        fun newInstance() = DiaryEntryListFragment()
    }
}
