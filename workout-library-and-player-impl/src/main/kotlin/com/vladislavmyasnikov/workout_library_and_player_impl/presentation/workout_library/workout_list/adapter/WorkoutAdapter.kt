package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.vladislavmyasnikov.common.arch.adapter.SelectableBaseAdapter
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout.ShortWorkout
import kotlinx.android.synthetic.main.item_workout.view.*
import javax.inject.Inject

class WorkoutAdapter @Inject constructor() : SelectableBaseAdapter<ShortWorkout>() {

    override fun constructViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_workout, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : SelectableBaseAdapter.ViewHolder.NonSelectableViewHolder<ShortWorkout>(view) {

        override fun bind(item: ShortWorkout) {
            val resID = itemView.context.resources.getIdentifier(item.avatarID, "drawable", itemView.context.packageName)
            itemView.workout_avatar.setImageDrawable(ContextCompat.getDrawable(itemView.context, resID))
            itemView.workout_title.text = item.title
        }
    }
}