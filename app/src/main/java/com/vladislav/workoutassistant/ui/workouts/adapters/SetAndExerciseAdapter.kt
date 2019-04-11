package com.vladislav.workoutassistant.ui.workouts.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

import com.vladislav.workoutassistant.R
import com.vladislav.workoutassistant.data.models.Item
import com.vladislav.workoutassistant.data.models.WorkoutExercise
import com.vladislav.workoutassistant.data.models.WorkoutSet
import com.vladislav.workoutassistant.databinding.ItemWorkoutExerciseBinding
import com.vladislav.workoutassistant.databinding.ItemWorkoutSetBinding
import com.vladislav.workoutassistant.ui.main.components.SimpleItemTouchHelperCallback
import com.vladislav.workoutassistant.ui.main.components.SimpleItemTouchHelperCallback.OnStartDragListener
import com.vladislav.workoutassistant.ui.main.interfaces.OnItemClickCallback

import java.util.Collections
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class SetAndExerciseAdapter(private val mCallback: OnItemClickCallback, private val mStartDragListener: OnStartDragListener, private val mMuscleGroups: List<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), SimpleItemTouchHelperCallback.ItemTouchHelperAdapter {

    private var mItems: MutableList<Any>? = null

    fun setList(items: MutableList<Any>) {
        mItems = items  //TODO: add DiffUtil
    }

    override fun getItemViewType(position: Int): Int {
        val `object` = mItems!![position]
        if (`object` is WorkoutSet) {
            return SET_ITEM_VIEW_TYPE
        }
        return if (`object` is WorkoutExercise) {
            EXERCISE_ITEM_VIEW_TYPE
        } else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            SET_ITEM_VIEW_TYPE -> {
                val setBinding = DataBindingUtil.inflate<ItemWorkoutSetBinding>(inflater, R.layout.item_workout_set, parent, false)
                setBinding.root.setTag(R.id.black_horizontal_divider, "")
                return SetViewHolder(setBinding)
            }
            EXERCISE_ITEM_VIEW_TYPE -> {
                val exerciseBinding = DataBindingUtil.inflate<ItemWorkoutExerciseBinding>(inflater, R.layout.item_workout_exercise, parent, false)
                exerciseBinding.callback = mCallback
                return ExerciseViewHolder(exerciseBinding)
            }
            else -> return null
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SetViewHolder) {
            holder.bind(mItems!![position] as WorkoutSet)
        } else if (holder is ExerciseViewHolder) {
            holder.bind(mItems!![position] as WorkoutExercise)
        }
    }

    override fun getItemCount(): Int {
        return if (mItems == null) 0 else mItems!!.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(mItems, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(mItems, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        mItems!!.removeAt(position)
        notifyItemRemoved(position)
    }

    internal inner class SetViewHolder(private val mBinding: ItemWorkoutSetBinding) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(set: WorkoutSet) {
            mBinding.name = "Set"
            mBinding.reps = set.setInfo.id
        }
    }

    internal inner class ExerciseViewHolder(private val mBinding: ItemWorkoutExerciseBinding) : RecyclerView.ViewHolder(mBinding.root), SimpleItemTouchHelperCallback.ItemTouchHelperViewHolder {

        fun bind(exercise: WorkoutExercise) {
            mBinding.name = exercise.exerciseInfo.name
            mBinding.reps = exercise.matchingInfo.amount
            mBinding.muscleGroup = mMuscleGroups[exercise.exerciseInfo.muscleGroupId].name
            mBinding.handle.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    mStartDragListener.onStartDrag(this@ExerciseViewHolder)
                }
                false
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    companion object {

        private val SET_ITEM_VIEW_TYPE = 1
        private val EXERCISE_ITEM_VIEW_TYPE = 2
    }
}
