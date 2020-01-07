package com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.feature_workout_library_impl.R
import kotlinx.android.synthetic.main.item_number_cell.view.*

class NumberCellAdapter(private val context: Context, private val items: List<Int>) : RecyclerView.Adapter<NumberCellAdapter.ViewHolder>() {

    var callback: OnItemClickCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_number_cell, parent, false)
        val holder = ViewHolder(view)

        holder.itemView.setOnClickListener {
            val item = items[holder.adapterPosition]
            callback?.onClick(item.toLong(), "")
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Int) {
            itemView.number_cell_field.text = item.toString()
        }
    }
}
