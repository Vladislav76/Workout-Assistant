package com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.vladislavmyasnikov.common.presentation.adapters.SelectableBaseAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutExercise
import kotlinx.android.synthetic.main.item_workout_exercise.view.*
import javax.inject.Inject

class WorkoutExerciseAdapter @Inject constructor() : SelectableBaseAdapter<WorkoutExercise>() {

    var workoutSetApproach: Int = 0
        set(value) {
            if (value >= 0 && value != field && value < itemCount) {
                field = value
                notifyItemRangeChanged(0, itemCount)
            }
        }

    override fun constructViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_workout_exercise, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SelectableBaseAdapter.ViewHolder<WorkoutExercise>, position: Int) {
        if (holder is ViewHolder) {
            holder.workoutSetApproach = workoutSetApproach
            holder.bind(currentItems[position])
        }
    }

    class ViewHolder(view: View) : SelectableBaseAdapter.ViewHolder.NonSelectableViewHolder<WorkoutExercise>(view) {

        var workoutSetApproach: Int = 0

        override fun bind(item: WorkoutExercise) {
            val resID = itemView.context.resources.getIdentifier(item.avatarID, "drawable", itemView.context.packageName)
            itemView.workout_exercise_icon.setImageDrawable(ContextCompat.getDrawable(itemView.context, resID))
            itemView.workout_exercise_title.text = item.title
            itemView.workout_exercise_reps.text = item.reps[workoutSetApproach].toString()
            itemView.workout_exercise_weight.text = item.weights[workoutSetApproach].toString()
        }
    }
}