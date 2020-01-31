package com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.vladislavmyasnikov.common.presentation.adapters.SelectableBaseAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.ShortWorkoutInfo
import kotlinx.android.synthetic.main.item_workout.view.*
import javax.inject.Inject

class WorkoutAdapter @Inject constructor() : SelectableBaseAdapter<ShortWorkoutInfo>() {

    override fun constructViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_workout, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : SelectableBaseAdapter.ViewHolder.NonSelectableViewHolder<ShortWorkoutInfo>(view) {

        override fun bind(item: ShortWorkoutInfo) {
            val resID = itemView.context.resources.getIdentifier(item.avatarID, "drawable", itemView.context.packageName)
            itemView.workout_avatar.setImageDrawable(ContextCompat.getDrawable(itemView.context, resID))
            itemView.workout_title.text = item.title
        }
    }
}