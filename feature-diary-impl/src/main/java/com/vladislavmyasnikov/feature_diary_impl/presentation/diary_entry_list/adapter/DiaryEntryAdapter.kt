package com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_list.adapter

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.vladislavmyasnikov.common.presentation.adapters.SelectableBaseAdapter
import com.vladislavmyasnikov.common.utils.DateFormatType
import com.vladislavmyasnikov.common.utils.TimePointFormatType
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.domain.entity.ShortDiaryEntry
import kotlinx.android.synthetic.main.item_non_selectable_diary_entry.view.*
import kotlinx.android.synthetic.main.item_selectable_diary_entry.view.*
import javax.inject.Inject
import kotlinx.android.synthetic.main.item_selectable_diary_entry.view.date_field as _date_field
import kotlinx.android.synthetic.main.item_selectable_diary_entry.view.duration_field as _duration_field
import kotlinx.android.synthetic.main.item_selectable_diary_entry.view.time_field as _time_field

class DiaryEntryAdapter @Inject constructor() : SelectableBaseAdapter<ShortDiaryEntry>() {

    override fun constructViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<ShortDiaryEntry> {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == BASE_VIEW_TYPE) {
            val view = inflater.inflate(R.layout.item_non_selectable_diary_entry, parent, false)
            NonSelectableViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_selectable_diary_entry, parent, false)
            SelectableViewHolder(view)
        }
    }

    class NonSelectableViewHolder(view: View) : ViewHolder.NonSelectableViewHolder<ShortDiaryEntry>(view) {

        override fun bind(item: ShortDiaryEntry) {
            itemView.workout_date.text = DateFormatType.DAY_MONTH_YEAR.format(item.date)
            itemView.workout_duration.text = TimePointFormatType.DURATION.format(item.duration)
            itemView.workout_productivity.text = "TBD"
            itemView.workout_name.text = "TBD"
        }
    }

    class SelectableViewHolder(view: View) : ViewHolder.SelectableViewHolder<ShortDiaryEntry>(view) {

        override fun bind(item: ShortDiaryEntry, isSelected: Boolean) {
            itemView._date_field.text = DateFormatType.DAY_MONTH_YEAR.format(item.date)
            itemView._time_field.text = TimePointFormatType.HOUR_MINUTE.formatInterval(item.startTime, item.endTime)
            itemView._duration_field.text = TimePointFormatType.HOUR_MINUTE_SECOND.format(item.duration)
            val colorId = if (isSelected) R.color.red else R.color.gray
            itemView.selection_view.setColorFilter(ContextCompat.getColor(itemView.context, colorId), PorterDuff.Mode.SRC_IN)
        }
    }
}