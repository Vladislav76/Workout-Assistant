package com.vladislavmyasnikov.common.presentation.view.components

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.common.R
import com.vladislavmyasnikov.common.interfaces.Identifiable
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.presentation.BaseAdapter
import com.vladislavmyasnikov.common.presentation.view.VMFragment

abstract class VMListFragment<T : Identifiable<T>> : VMFragment<List<T>>(R.layout.linear_recycler_view) {

    override val label = "vm_list_fragment"

    protected abstract val itemClickCallback: OnItemClickCallback
    protected abstract val adapter: BaseAdapter<T>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter
        adapter.callback = itemClickCallback
        if (!adapter.hasObservers()) adapter.setHasStableIds(true)
    }

    override fun onReceiveItem(item: List<T>) {
        adapter.setList(item)
    }
}