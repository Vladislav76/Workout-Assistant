package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.vladislavmyasnikov.common.presentation.adapters.SelectableBaseAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutExercise
import kotlinx.android.synthetic.main.item_workout_exercise.view.*
import javax.inject.Inject

class WorkoutExerciseAdapter @Inject constructor() : SelectableBaseAdapter<WorkoutExercise>() {

    override fun constructViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_workout_exercise, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SelectableBaseAdapter.ViewHolder<WorkoutExercise>, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(currentItems[position])
        }
    }

    class ViewHolder(view: View) : SelectableBaseAdapter.ViewHolder.NonSelectableViewHolder<WorkoutExercise>(view) {

        override fun bind(item: WorkoutExercise) {
            val resID = itemView.context.resources.getIdentifier(item.info.avatarID, "drawable", itemView.context.packageName)
            itemView.workout_exercise_icon.setImageDrawable(ContextCompat.getDrawable(itemView.context, resID))
            itemView.workout_exercise_title.text = item.info.title
            itemView.workout_exercise_reps.text = item.workoutExerciseIndicators.reps.toString()
            itemView.workout_exercise_weight.text = item.workoutExerciseIndicators.weight.toString()
        }
    }
}