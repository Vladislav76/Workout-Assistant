package com.vladislav.workoutassistant.ui.workouts.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.vladislav.workoutassistant.R
import com.vladislav.workoutassistant.data.models.Item
import com.vladislav.workoutassistant.ui.main.interfaces.OnItemClickCallback
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(private val mItems: List<Item>, private val mCallback: OnItemClickCallback) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var lastSelectedItemPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_category, parent, false)
        val holder = ViewHolder(view)

        holder.itemView.setOnClickListener {
            val position: Int = holder.adapterPosition
            val item = mItems[position]
            mCallback.onClick(item.id, item.name)

            if (position != lastSelectedItemPosition) {
                notifyItemChanged(position)
                notifyItemChanged(lastSelectedItemPosition)
                lastSelectedItemPosition = position
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mItems[position], position == lastSelectedItemPosition)
    }

    override fun getItemCount(): Int = mItems.size



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mTitleView = view.findViewById<TextView>(R.id.category_item_title)

        fun bind(item: Item, isSelected: Boolean) {
            mTitleView.text = item.name
            if (isSelected) {
                mTitleView.paintFlags = mTitleView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            } else {
                mTitleView.paintFlags = mTitleView.paintFlags and Paint.LINEAR_TEXT_FLAG
            }
        }
    }
}
