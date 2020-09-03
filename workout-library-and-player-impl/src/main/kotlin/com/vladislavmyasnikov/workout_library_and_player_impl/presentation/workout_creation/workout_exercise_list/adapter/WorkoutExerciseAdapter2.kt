package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.vladislavmyasnikov.common.arch.adapter.SelectableBaseAdapter
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutExercise
import kotlinx.android.synthetic.main.item_workout_exercise_2.view.*
import javax.inject.Inject

class WorkoutExerciseAdapter2 @Inject constructor() : SelectableBaseAdapter<WorkoutExercise>() {

    override fun constructViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_workout_exercise_2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SelectableBaseAdapter.ViewHolder<WorkoutExercise>, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(currentItems[position])
        }
    }

    class ViewHolder(view: View) : SelectableBaseAdapter.ViewHolder.NonSelectableViewHolder<WorkoutExercise>(view) {

        override fun bind(item: WorkoutExercise) {
            itemView.workout_exercise_name.text = item.name
            with (itemView.context) {
                val resID = resources.getIdentifier(item.avatarId, "drawable", packageName)
                itemView.workout_exercise_avatar.setImageDrawable(ContextCompat.getDrawable(this, resID))
            }

            val columnCount = 3
            val n = item.cycles.size
            val n1 = n / columnCount
            val n2 = n % columnCount
            var currentLineCount = 0
            for (i in 0 until columnCount) {
                val lineCount = n1 + if (i < n2) 1 else 0
                if (lineCount > 0) {
                    val cycleView = when (i) {
                        0 -> itemView.workout_exercise_cycles_1
                        1 -> itemView.workout_exercise_cycles_2
                        else -> itemView.workout_exercise_cycles_3
                    }
                    val subList = item.cycles.subList(currentLineCount, currentLineCount + lineCount)
                    cycleView.text = subList.joinToString(separator = "\n", transform = { "#${++currentLineCount}.   ${it.weight} kg\nx${it.reps}" })
                }
            }
        }
    }
}