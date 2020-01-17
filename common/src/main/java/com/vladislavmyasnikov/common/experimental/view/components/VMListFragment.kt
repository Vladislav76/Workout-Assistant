package com.vladislavmyasnikov.common.experimental.view.components

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.common.R
import com.vladislavmyasnikov.common.experimental.BaseAdapter
import com.vladislavmyasnikov.common.experimental.view.VMFragment
import com.vladislavmyasnikov.common.interfaces.Identifiable
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback

abstract class VMListFragment<T : Identifiable<T>> : VMFragment<List<T>>(R.layout.linear_recycler_view) {

    override val label = "vm_list_fragment"

    protected abstract val itemClickCallback: OnItemClickCallback
    protected abstract val adapter: BaseAdapter<T>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter
        adapter.callback = itemClickCallback
    }

    override fun onReceiveItem(item: List<T>) {
        adapter.setList(item)
    }
/*

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.callbackInSelectMode = itemClickCallbackInSelectMode

        ///////////////////////////////////
           if (!adapter.hasObservers()) {
            adapter.setHasStableIds(true)
            adapter.callback = itemClickCallback
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
        /////////////////////////////////
        when (item) {
            GeneralViewModel.LOADED_REQUEST_RESULT -> adapter.setList(viewModel.exercisesInfo)
        }
    }

 */
}