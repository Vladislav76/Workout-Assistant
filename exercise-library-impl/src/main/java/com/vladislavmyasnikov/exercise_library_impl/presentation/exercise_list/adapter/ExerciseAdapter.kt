package com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.vladislavmyasnikov.common.presentation.adapters.SelectableBaseAdapter
import com.vladislavmyasnikov.exercise_library_impl.R
import com.vladislavmyasnikov.exercise_library_impl.domain.entity.ShortExercise
import kotlinx.android.synthetic.main.item_exercise.view.*
import javax.inject.Inject

class ExerciseAdapter @Inject constructor() : SelectableBaseAdapter<ShortExercise>() {

    override fun constructViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_exercise, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : SelectableBaseAdapter.ViewHolder.NonSelectableViewHolder<ShortExercise>(view) {

        override fun bind(item: ShortExercise) {
            itemView.exercise_name.text = item.title

            val muscleGroupNames = itemView.context.resources.getStringArray(R.array.muscle_groups)
            itemView.muscle_groups_field.text = item.muscleGroupsIDs.joinToString(transform = { id -> muscleGroupNames[id] })

            val resID = itemView.context.resources.getIdentifier(item.avatarID, "drawable", itemView.context.packageName)
            itemView.exercise_icon.setImageDrawable(ContextCompat.getDrawable(itemView.context, resID))
        }
    }
}