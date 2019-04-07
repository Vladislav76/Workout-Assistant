package com.vladislav.workoutassistant.ui.exercises.adapters

import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vladislav.workoutassistant.R
import com.vladislav.workoutassistant.utilities.DiffUtilCallback
import com.vladislav.workoutassistant.ui.main.interfaces.OnItemClickCallback
import com.vladislav.workoutassistant.data.db.entity.Exercise
import com.vladislav.workoutassistant.databinding.ItemExerciseBinding
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter(private val mCallback: OnItemClickCallback, private val mSelectedExercises: SparseIntArray) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    private var mExercises: List<Exercise> = listOf()

    fun updateList(exercises: List<Exercise>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(mExercises, exercises))
        mExercises = exercises
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateLastSelectedItem() {
        notifyItemChanged(lastSelectedItemPosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemExerciseBinding>(inflater, R.layout.item_exercise, parent, false)
        return ExerciseAdapter.ViewHolder(binding, mCallback)
    }

    override fun onBindViewHolder(holder: ExerciseAdapter.ViewHolder, position: Int) {
        val exercise: Exercise = mExercises[position]
        val amount: Int = mSelectedExercises.get(exercise.id, -1)
        holder.bind(mExercises[position], amount)
    }

    override fun getItemCount(): Int = mExercises.size



    class ViewHolder(private val mBinding: ItemExerciseBinding, val mCallback: OnItemClickCallback) : RecyclerView.ViewHolder(mBinding.root) {
        private var mExercise: Exercise? = null

        init {
            itemView.setOnClickListener {
                lastSelectedItemPosition = adapterPosition
                mCallback.onClick(mExercise!!.id, mExercise!!.name) }
        }

        fun bind(exercise: Exercise, amount: Int) {
            mExercise = exercise
            mBinding.name = exercise.name

            if (amount >= 0) {
                mBinding.selected = true
                mBinding.count = amount
            } else {
                mBinding.selected = false
            }
        }
    }



    companion object SharedData {
        var lastSelectedItemPosition: Int = 0
    }
}
