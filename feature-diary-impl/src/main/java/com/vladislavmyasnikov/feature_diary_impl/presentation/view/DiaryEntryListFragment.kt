package com.vladislavmyasnikov.feature_diary_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.common.components.GeneralViewModel
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.view.GeneralFragment
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.di.DiaryFeatureComponent
import com.vladislavmyasnikov.feature_diary_impl.presentation.Screens
import com.vladislavmyasnikov.feature_diary_impl.presentation.adapters.ShortDiaryEntryAdapter
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.DiaryEntryListViewModel
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class DiaryEntryListFragment : GeneralFragment<DiaryEntryListViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: ShortDiaryEntryAdapter

    private val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            val screen = Screens.DiaryEntryDetailsScreen(id, title)
            router.navigateTo(screen)
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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DiaryEntryListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.linear_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter

        adapter.callback = itemClickCallback
        adapter.callbackInSelectMode = itemClickCallbackInSelectMode
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val menuRes = when (viewModel.mode) {
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
                viewModel.mode = DiaryEntryListViewModel.DELETE_MODE
                setSelectModeAndUpdate(true)
                true
            }
            R.id.delete_diary_entries_forever_action -> {
                viewModel.deleteEntriesByIDs(adapter.getSelectedItemIDs())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun <Int> onReceiveItem(item: Int) {
        when (item) {
            GeneralViewModel.LOADED_REQUEST_RESULT -> {
                adapter.updateList(viewModel.entries)
            }
            GeneralViewModel.DELETED_REQUEST_RESULT -> {
                viewModel.mode = DiaryEntryListViewModel.NORMAL_MODE
                setSelectModeAndUpdate(false)
            }
        }
    }

    override fun onBackPressed(): Boolean {
        return if (viewModel.mode == DiaryEntryListViewModel.DELETE_MODE) {
            viewModel.mode = DiaryEntryListViewModel.NORMAL_MODE
            setSelectModeAndUpdate(false)
            true
        } else {
            super.onBackPressed()
        }
    }

    override fun updateToolbar() {
        super.updateToolbar()
        updateTitle()
        screenTitleController.setDisplayHomeAsUpEnabled(viewModel.mode == DiaryEntryListViewModel.DELETE_MODE)
        setHasOptionsMenu(true)
    }

    private fun updateTitle() {
        if (viewModel.mode == DiaryEntryListViewModel.DELETE_MODE) {
            screenTitleController.setTitle("${adapter.selectedItemCount}")
        } else {
            screenTitleController.setTitle(R.string.diary_entry_list_title)
        }
    }

    private fun setSelectModeAndUpdate(isSelectModeTurnedOn: Boolean) {
        adapter.setSelectMode(isSelectModeTurnedOn)
        updateToolbar()
    }

    companion object {
        fun newInstance() = DiaryEntryListFragment()
    }
}
