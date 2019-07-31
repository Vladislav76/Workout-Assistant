package com.vladislavmyasnikov.feature_diary_impl.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.core_components.components.DateFormatter
import com.vladislavmyasnikov.core_components.components.TimePointFormatter
import com.vladislavmyasnikov.core_components.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.core_utils.utils.utils.DiffUtilCallback
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.domain.ShortDiaryEntry
import kotlinx.android.synthetic.main.item_diary_entry.view.*

class ShortDiaryEntryAdapter : RecyclerView.Adapter<ShortDiaryEntryAdapter.ViewHolder>() {

    var callback: OnItemClickCallback? = null
    private var items: List<ShortDiaryEntry> = emptyList()

    fun updateList(_items: List<ShortDiaryEntry>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(items, _items))
        items = _items
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_diary_entry, parent, false)
        val holder = ViewHolder(view)

        holder.itemView.setOnClickListener {
            val item = items[holder.adapterPosition]
            callback?.onClick(item.id, "Workout#id${item.id}")
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long = items[position].id //TODO: why does it need?

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: ShortDiaryEntry) {
            itemView.date_field.text = DateFormatter.format(item.date, DateFormatter.DAY_MONTH_YEAR_FORMAT)
            itemView.time_field.text = TimePointFormatter.formatInterval(item.startTime, item.endTime, TimePointFormatter.HOUR_MINUTE_FORMAT)
            itemView.duration_field.text = TimePointFormatter.format(item.duration, TimePointFormatter.HOUR_MINUTE_FORMAT)
        }
    }
}