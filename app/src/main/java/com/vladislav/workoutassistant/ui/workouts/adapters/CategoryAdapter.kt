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

class CategoryAdapter(private val mCategories: List<Item>?, private val mCallback: OnItemClickCallback) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private var mSelectedItemPosition: Int = 0

    fun setItemPosition(position: Int) {
        //notifyItemChanged(mSelectedItemPosition);
        mSelectedItemPosition = position
        notifyItemChanged(mSelectedItemPosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(mCategories!![position])
    }

    override fun getItemCount(): Int {
        return mCategories?.size ?: 0
    }

    internal inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val mTitleView: TextView
        private var mCategory: Item? = null

        init {
            mTitleView = view.findViewById(R.id.category_item_title)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            if (adapterPosition != mSelectedItemPosition) {
                notifyItemChanged(mSelectedItemPosition)
                mSelectedItemPosition = adapterPosition
                notifyItemChanged(mSelectedItemPosition)
            }
            mCallback.onClick(mCategory!!.id, mCategory!!.name)
        }

        fun bind(category: Item) {
            if (adapterPosition == mSelectedItemPosition) {
                mTitleView.paintFlags = mTitleView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            } else {
                mTitleView.paintFlags = mTitleView.paintFlags and Paint.LINEAR_TEXT_FLAG
            }
            mCategory = category
            mTitleView.text = category.name
        }
    }
}
