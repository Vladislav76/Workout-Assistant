package com.vladislavmyasnikov.common.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.common.interfaces.Identifiable
import com.vladislavmyasnikov.common.utils.DiffUtilCallback

abstract class BaseAdapter<T : Identifiable<T>, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    protected var currentItems: List<T> = emptyList()

    override fun getItemCount() = currentItems.size

    override fun getItemId(position: Int) = currentItems[position].id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val holder = constructViewHolder(parent, viewType)
        handleViewHolder(holder)
        return holder
    }

    fun setList(items: List<T>) {
        updateList(items)
    }

    protected abstract fun handleViewHolder(holder: VH)

    protected abstract fun constructViewHolder(parent: ViewGroup, viewType: Int): VH

    private fun updateList(items: List<T>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(currentItems, items))
        currentItems = items
        diffResult.dispatchUpdatesTo(this)
    }
}