package com.vladislavmyasnikov.common.presentation

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.common.interfaces.Identifiable
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.utils.DiffUtilCallback

abstract class BaseAdapter<T : Identifiable<T>> : RecyclerView.Adapter<BaseAdapter.ViewHolder<T>>() {

    var callback: OnItemClickCallback? = null

    private var currentItems: List<T> = emptyList()
    private var sourceItems: List<T> = emptyList()

    override fun getItemCount() = currentItems.size

    override fun getItemId(position: Int) = currentItems[position].id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val holder = constructViewHolder(parent, viewType)

        holder.itemView.setOnClickListener {
            val item = currentItems[holder.adapterPosition]
            callback?.onClick(item.id, "Fake title")
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bind(currentItems[position])
    }

    fun setList(items: List<T>) {
        sourceItems = items
        updateList(items)
    }

    protected abstract fun constructViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T>

    private fun updateList(items: List<T>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(currentItems, items))
        currentItems = items
        diffResult.dispatchUpdatesTo(this)
    }

    abstract class ViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

        abstract fun bind(item: T)
    }
}