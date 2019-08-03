package com.vladislavmyasnikov.feature_diary_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.core_components.components.GeneralViewModel
import com.vladislavmyasnikov.core_components.interfaces.FragmentController
import com.vladislavmyasnikov.core_components.interfaces.OnBackPressedListener
import com.vladislavmyasnikov.core_components.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.core_components.interfaces.ScreenTitleController
import com.vladislavmyasnikov.core_utils.utils.utils.Logger
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.di.DiaryFeatureComponent
import com.vladislavmyasnikov.feature_diary_impl.presentation.adapters.ShortDiaryEntryAdapter
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.DiaryEntryListViewModel
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.DiaryViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DiaryEntryListFragment : Fragment(), OnBackPressedListener {

    @Inject
    lateinit var viewModelFactory: DiaryViewModelFactory

    @Inject
    lateinit var adapter: ShortDiaryEntryAdapter

    @Inject
    lateinit var screenTitleController: ScreenTitleController

    @Inject
    lateinit var fragmentController: FragmentController

    private lateinit var diaryVM: DiaryEntryListViewModel
    private val disposables = CompositeDisposable()

    private val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            val fragment = DiaryEntryFragment.newInstance(id, title)
            fragmentController.addFragmentOnTop(fragment)
        }
    }

    private val itemClickCallbackInSelectMode = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            updateTitle()
        }
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
        updateTitle()
        screenTitleController.setDisplayHomeAsUpEnabled(diaryVM.mode == DiaryEntryListViewModel.DELETE_MODE)
        setHasOptionsMenu(true)

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
                    when (it) {
                        GeneralViewModel.LOADED_REQUEST_RESULT -> {
                            adapter.updateList(diaryVM.entries)
                            Logger.debug(TAG, "Entries fetching: SUCCESS; Amount: ${diaryVM.entries.size}")
                        }
                        GeneralViewModel.DELETED_REQUEST_RESULT -> {
                            diaryVM.mode = DiaryEntryListViewModel.NORMAL_MODE
                            screenTitleController.setDisplayHomeAsUpEnabled(false)
                            setSelectModeAndUpdate(false)
                            diaryVM.fetchShortEntries() //TODO: may be notify adapter that items was removed
                        }
                    }
                })

        if (savedInstanceState == null) {
            adapter.callback = itemClickCallback
            adapter.callbackInSelectMode = itemClickCallbackInSelectMode

            diaryVM.fetchShortEntries()
            Logger.debug(TAG, "Entries fetching: REQUEST")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val menuRes = when (diaryVM.mode) {
            DiaryEntryListViewModel.DELETE_MODE -> R.menu.menu_delete_entries_action
            else -> R.menu.menu_diary_entry_list
        }
        inflater.inflate(menuRes, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_diary_entry_action -> {
                itemClickCallback.onClick(0, "New entry")
                true
            }
            R.id.delete_diary_entries_action -> {
                diaryVM.mode = DiaryEntryListViewModel.DELETE_MODE
                screenTitleController.setDisplayHomeAsUpEnabled(true)
                setSelectModeAndUpdate(true)
                true
            }
            R.id.delete_diary_entries_forever_action -> {
                for (id in adapter.getSelectedItemIDs()) {
                    println("id = $id")
                }
                diaryVM.deleteEntriesByIDs(adapter.getSelectedItemIDs())
                true
            }
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed(): Boolean {
        return if (diaryVM.mode == DiaryEntryListViewModel.DELETE_MODE) {
            diaryVM.mode = DiaryEntryListViewModel.NORMAL_MODE
            screenTitleController.setDisplayHomeAsUpEnabled(false)
            setSelectModeAndUpdate(false)
            true
        } else false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    private fun updateTitle() {
        if (diaryVM.mode == DiaryEntryListViewModel.DELETE_MODE) {
            screenTitleController.setTitle("${adapter.selectedItemCount}")
        }
        else {
            screenTitleController.setTitle(R.string.diary_entry_list_title)
        }
    }

    private fun setSelectModeAndUpdate(isSelectModeTurnedOn: Boolean) {
        updateTitle()
        activity?.invalidateOptionsMenu()
        adapter.setSelectMode(isSelectModeTurnedOn)
    }

    companion object {
        private const val TAG = "DIARY_ENTRY_LIST_FRAGMENT"

        fun newInstance() = DiaryEntryListFragment()
    }
}
