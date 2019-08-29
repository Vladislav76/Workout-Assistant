package com.vladislavmyasnikov.feature_diary_impl.presentation.adapters

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.common.utils.DateFormatter
import com.vladislavmyasnikov.common.utils.TimePointFormatter
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.utils.DiffUtilCallback
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.domain.ShortDiaryEntry
import kotlinx.android.synthetic.main.item_diary_entry.view.*
import javax.inject.Inject

class ShortDiaryEntryAdapter @Inject constructor(private val context: Context) : RecyclerView.Adapter<ShortDiaryEntryAdapter.ViewHolder>() {

    var callback: OnItemClickCallback? = null
    var callbackInSelectMode: OnItemClickCallback? = null
    var selectedItemCount: Int = 0
        private set

    private var items: List<ShortDiaryEntry> = emptyList()
    private var selectedItems: BooleanArray? = null
    private var isSelectMode: Boolean = false

    fun updateList(_items: List<ShortDiaryEntry>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(items, _items))
        items = _items
        diffResult.dispatchUpdatesTo(this)
    }

    fun getSelectedItemIDs(): List<Long> {
        var currentIndex = -1
        return selectedItems?.let { array ->
            array.asSequence().filter { value -> currentIndex++; value }.map { items[currentIndex].id }.toList()
        } ?: emptyList()
    }

    fun setSelectMode(isTurnedOn: Boolean) {
        if (isSelectMode xor isTurnedOn) {
            if (isSelectMode) {
                isSelectMode = false
                selectedItems = null
                selectedItemCount = 0
            } else {
                isSelectMode = true
                selectedItems = BooleanArray(items.size)
            }
            notifyItemRangeChanged(0, items.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_diary_entry, parent, false)
        val holder = ViewHolder(view, context)

        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val item = items[position]

            if (isSelectMode) {
                selectedItems!!.apply {
                    val wasSelected = get(holder.adapterPosition)
                    set(position, !wasSelected)
                    selectedItemCount += if (wasSelected) -1 else 1
                    notifyItemChanged(position)
                }
                callbackInSelectMode?.onClick(item.id, "")
            } else {
                callback?.onClick(item.id, "Workout#id${item.id}")
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], selectedItems?.get(position))
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long = items[position].id //TODO: why does it need?

    class ViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {

        fun bind(item: ShortDiaryEntry, isSelectedItem: Boolean?) {
            itemView.date_field.text = DateFormatter.format(item.date, DateFormatter.DAY_MONTH_YEAR_FORMAT)
            itemView.time_field.text = TimePointFormatter.formatInterval(item.startTime, item.endTime, TimePointFormatter.HOUR_MINUTE_FORMAT)
            itemView.duration_field.text = TimePointFormatter.format(item.duration, TimePointFormatter.HOUR_MINUTE_FORMAT)
            if (isSelectedItem != null) {
                itemView.selection_view.visibility = View.VISIBLE
                val colorId = if (isSelectedItem) R.color.red else R.color.gray
                itemView.selection_view.setColorFilter(ContextCompat.getColor(context, colorId), PorterDuff.Mode.SRC_IN)
            } else {
                itemView.selection_view.visibility = View.GONE
            }
        }
    }
}