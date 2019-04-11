package com.vladislav.workoutassistant.ui.exercises.adapters

import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vladislav.workoutassistant.R
import com.vladislav.workoutassistant.data.db.entity.Exercise
import com.vladislav.workoutassistant.databinding.ItemExerciseBinding
import com.vladislav.workoutassistant.ui.main.interfaces.OnItemClickCallback
import com.vladislav.workoutassistant.utilities.DiffUtilCallback

class ExerciseAdapter(private val mCallback: OnItemClickCallback, private val mSelectedItems: SparseIntArray) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    var lastSelectedItemPosition: Int = 0

    private var mItems: List<Exercise> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemExerciseBinding>(inflater, R.layout.item_exercise, parent, false)
        val holder = ViewHolder(binding)

        holder.itemView.setOnClickListener {
            val item = mItems[holder.adapterPosition]
            mCallback.onClick(item.id, item.name)
            lastSelectedItemPosition = holder.adapterPosition
        }
        return holder
    }

    override fun onBindViewHolder(holder: ExerciseAdapter.ViewHolder, position: Int) {
        val item = mItems[position]
        val amount: Int = mSelectedItems.get(item.id, -1)
        holder.bind(item, amount)
    }

    override fun getItemCount(): Int = mItems.size

    fun updateList(items: List<Exercise>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(mItems, items))
        mItems = items
        diffResult.dispatchUpdatesTo(this)
    }



    class ViewHolder(private val mBinding: ItemExerciseBinding) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(item: Exercise, amount: Int) {
            mBinding.name = item.name
            if (amount >= 0) {
                mBinding.selected = true
                mBinding.count = amount
            } else {
                mBinding.selected = false
            }
        }
    }
}
