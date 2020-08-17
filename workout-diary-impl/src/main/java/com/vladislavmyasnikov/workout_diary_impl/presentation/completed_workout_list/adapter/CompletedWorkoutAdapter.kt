package com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vladislavmyasnikov.common.presentation.adapters.SelectableBaseAdapter
import com.vladislavmyasnikov.common.utils.DateFormatType
import com.vladislavmyasnikov.common.utils.TimePointFormatType
import com.vladislavmyasnikov.workout_diary_impl.R
import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.ShortCompletedWorkout
import kotlinx.android.synthetic.main.item_non_selectable_diary_entry.view.*
import javax.inject.Inject

class CompletedWorkoutAdapter @Inject constructor() : SelectableBaseAdapter<ShortCompletedWorkout>() {

    override fun constructViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<ShortCompletedWorkout> {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == BASE_VIEW_TYPE) {
            val view = inflater.inflate(R.layout.item_non_selectable_diary_entry, parent, false)
            NonSelectableViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_selectable_diary_entry, parent, false)
            SelectableViewHolder(view)
        }
    }

    class NonSelectableViewHolder(view: View) : ViewHolder.NonSelectableViewHolder<ShortCompletedWorkout>(view) {

        override fun bind(item: ShortCompletedWorkout) {
            itemView.workout_date.text = DateFormatType.DAY_MONTH_YEAR.format(item.date)
            itemView.workout_duration.text = TimePointFormatType.DURATION.format(item.duration)
            itemView.workout_productivity.text = "${item.workoutProductivity}%"
            itemView.workout_name.text = item.workoutName
        }
    }

    // TODO: delete this
    class SelectableViewHolder(view: View) : ViewHolder.SelectableViewHolder<ShortCompletedWorkout>(view) {

        override fun bind(item: ShortCompletedWorkout, isSelected: Boolean) {}
    }
}