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

    private var mWorkouts: List<Workout> = listOf()

    fun updateList(workouts: List<Workout>) {
        mWorkouts = workouts   //TODO: add DiffUtil
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_workout, parent, false)
        val holder = WorkoutViewHolder(view)

        holder.itemView.setOnClickListener {
            val workout: Workout = mWorkouts[holder.adapterPosition]
            mCallback.onClick(workout.id, workout.name)
        }
        return holder
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        holder.bind(mWorkouts[position])
    }

    override fun getItemCount(): Int = mWorkouts.size



    class WorkoutViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mTitleView = view.findViewById<TextView>(R.id.card_title)
        private val mSubtitleView = view.findViewById<TextView>(R.id.card_subtitle)

        fun bind(workout: Workout) {
            mTitleView.text = workout.name
            mSubtitleView.text = workout.id.toString()
        }
    }
}
