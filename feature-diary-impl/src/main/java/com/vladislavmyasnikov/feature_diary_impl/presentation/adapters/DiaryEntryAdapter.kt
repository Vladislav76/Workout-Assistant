package com.vladislavmyasnikov.feature_diary_impl.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.core_utils.utils.utils.DiffUtilCallback
import com.vladislavmyasnikov.core_components.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryEntry

class DiaryEntryAdapter : RecyclerView.Adapter<DiaryEntryAdapter.ViewHolder>() {

    var callback: OnItemClickCallback? = null
    private var items: List<DiaryEntry> = emptyList()

    fun updateList(_items: List<DiaryEntry>) {
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
            callback?.onClick(item.id, "Diary Entry")
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long = items[position].id //why does it need?

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.title_field)
        //other

        fun bind(item: DiaryEntry) {
            title.text = "Workout ${item.id}"
        }
    }
}