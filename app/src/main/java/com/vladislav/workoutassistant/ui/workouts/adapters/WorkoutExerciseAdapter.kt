package com.vladislav.workoutassistant.ui.workouts.adapters

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vladislav.workoutassistant.R
import com.vladislav.workoutassistant.data.DataRepository
import com.vladislav.workoutassistant.data.models.ExerciseInfo
import com.vladislav.workoutassistant.data.models.Item
import com.vladislav.workoutassistant.data.models.SetInfo
import com.vladislav.workoutassistant.databinding.ItemWorkoutExerciseBinding
import com.vladislav.workoutassistant.databinding.ItemWorkoutSetBinding
import com.vladislav.workoutassistant.ui.main.interfaces.OnItemClickCallback

class WorkoutExerciseAdapter(application: Application, private val mCallback : OnItemClickCallback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mMuscleGroups: List<Item> = DataRepository.getInstance(application).loadMuscleGroups()
    private var mSets: List<SetInfo> = listOf()
    private val mSetPositions = ArrayList<Int>()
    private var mItemsNumber = 0

    fun updateContent(sets: List<SetInfo>) {
        mSets = sets  //TODO: add DiffUtil
        changeSetPositions()
        notifyDataSetChanged()
    }

    private fun changeSetPositions() {
        mSetPositions.clear()
        mItemsNumber = 0
        for (set in mSets) {
            mSetPositions.add(mItemsNumber)
            mItemsNumber += set.exercises.size + 1
        }
        println(mSetPositions.toString())
    }

    override fun getItemViewType(position: Int): Int = if (mSetPositions.contains(position)) SET_TYPE else EXERCISE_TYPE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            SET_TYPE -> {
                val setBinding = DataBindingUtil.inflate<ItemWorkoutSetBinding>(inflater, R.layout.item_workout_set, parent, false)
                setBinding.root.setTag(R.id.black_horizontal_divider, "")
                val holder = SetViewHolder(setBinding)
                holder.itemView.setOnClickListener {
                    val set = getItemAtPosition(holder.adapterPosition) as SetInfo
                    mCallback.onClick(set.id, "Set") //TODO: send amount
                }
                holder
            }
            else -> {
                val exerciseBinding = DataBindingUtil.inflate<ItemWorkoutExerciseBinding>(inflater, R.layout.item_workout_exercise, parent, false)
                val holder = ExerciseViewHolder(exerciseBinding)
                holder.itemView.setOnClickListener {
                    val exercise = getItemAtPosition(holder.adapterPosition) as ExerciseInfo
                    mCallback.onClick(exercise.id, "Exercise") //TODO: send amount
                }
                holder
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SetViewHolder -> {
                holder.bind(getItemAtPosition(position) as SetInfo)
            }
            is ExerciseViewHolder -> {
                holder.bind(getItemAtPosition(position) as ExerciseInfo, mMuscleGroups)
            }
        }
    }

    override fun getItemCount(): Int = mItemsNumber

    private fun getItemAtPosition(position: Int): Any {
        val setIndex: Int = mSetPositions.indexOfLast { it <= position }   //maybe -1 if not found
        val setPosition: Int = mSetPositions[setIndex]
        val set = mSets[setIndex]
        return if (setPosition == position) set else set.exercises[position - setPosition - 1]
    }



    class SetViewHolder(private val mBinding: ItemWorkoutSetBinding) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(item: SetInfo) {
            mBinding.name = "Set"
            mBinding.reps = item.amount
        }
    }



    class ExerciseViewHolder(private val mBinding: ItemWorkoutExerciseBinding) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(item: ExerciseInfo, muscleGroups: List<Item>) {
            mBinding.name = item.name
            mBinding.reps = item.amount
            mBinding.muscleGroup = muscleGroups[item.muscleGroupId].name
        }
    }



    companion object {

        private const val SET_TYPE = 1
        private const val EXERCISE_TYPE = 2
    }
}