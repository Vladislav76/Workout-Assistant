package com.vladislavmyasnikov.common.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.common.interfaces.Identifiable
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback

abstract class SelectableBaseAdapter<T : Identifiable<T>> : BaseAdapter<T, SelectableBaseAdapter.ViewHolder<T>>() {

    companion object {
        const val BASE_VIEW_TYPE = 1
        const val SELECTION_VIEW_TYPE = 2
    }

    var callback: OnItemClickCallback? = null
    var callbackInSelectMode: OnItemClickCallback? = null

    var selectedItemAmount = 0
        private set

    private var selectionStatus = booleanArrayOf()

    var isSelectMode = false
        set(value) {
            if (field != value) {
                if (value) selectionStatus = BooleanArray(currentItems.size) { false }
                notifyItemRangeChanged(0, currentItems.size)  // or notifyDataSetChanged()
                field = value
            }
        }

    fun getSelectedItemIDs(): List<Long> =
            selectionStatus
                    .asSequence()
                    .filter { isSelected -> isSelected }
                    .mapIndexed { index, _ -> currentItems[index].id }
                    .toList()

    override fun getItemViewType(position: Int): Int = if (isSelectMode) SELECTION_VIEW_TYPE else BASE_VIEW_TYPE

    override fun handleViewHolder(holder: ViewHolder<T>) {
        if (isSelectMode) {
            holder.itemView.setOnClickListener {
                val currentPosition = holder.adapterPosition
                val item = currentItems[currentPosition]
                val wasSelected = selectionStatus[currentPosition]

                selectionStatus[currentPosition] = !wasSelected
                selectedItemAmount += if (wasSelected) -1 else 1
                notifyItemChanged(currentPosition)

                callbackInSelectMode?.onClick(item.id, "")
            }
        } else {
            holder.itemView.setOnClickListener {
                val item = currentItems[holder.adapterPosition]
                callback?.onClick(item.id, "")
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        when (holder) {
            is ViewHolder.NonSelectableViewHolder -> holder.bind(currentItems[position])
            is ViewHolder.SelectableViewHolder -> holder.bind(currentItems[position], selectionStatus[position])
        }
    }

    sealed class ViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

        abstract class NonSelectableViewHolder<T>(view: View) : ViewHolder<T>(view) {

            abstract fun bind(item: T)
        }

        abstract class SelectableViewHolder<T>(view: View) : ViewHolder<T>(view) {

            abstract fun bind(item: T, isSelected: Boolean)
        }
    }
}