package com.vladislav.workoutassistant.ui.workouts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.vladislav.workoutassistant.R
import com.vladislav.workoutassistant.ui.main.interfaces.OnItemClickCallback
import com.vladislav.workoutassistant.data.db.entity.Workout
import androidx.recyclerview.widget.RecyclerView

class WorkoutAdapter(private val mCallback: OnItemClickCallback) : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    private var mWorkouts: List<Workout>? = null

    fun setList(workouts: List<Workout>) {
        mWorkouts = workouts   //TODO: add DiffUtil
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_workout, parent, false)
        return WorkoutAdapter.WorkoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        holder.bind(mWorkouts!![position])
    }

    override fun getItemCount(): Int {
        return if (mWorkouts == null) 0 else mWorkouts!!.size
    }

    internal inner class WorkoutViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mTitleView: TextView
        private val mSubtitleView: TextView
        private var mWorkout: Workout? = null

        init {
            mTitleView = view.findViewById(R.id.card_title)
            mSubtitleView = view.findViewById(R.id.card_subtitle)
            view.setOnClickListener { mCallback.onClick(mWorkout!!.id, mWorkout!!.name) }
        }

        fun bind(workout: Workout) {
            mWorkout = workout
            mTitleView.text = workout.name
            mSubtitleView.text = Integer.toString(workout.id)
        }
    }
}
