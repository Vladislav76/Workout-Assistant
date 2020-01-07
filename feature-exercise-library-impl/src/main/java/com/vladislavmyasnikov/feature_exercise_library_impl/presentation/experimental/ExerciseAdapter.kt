package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.vladislavmyasnikov.common.experimental.BaseAdapter
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ShortExerciseInfo
import kotlinx.android.synthetic.main.item_exercise.view.*

class ExerciseAdapter : BaseAdapter<ShortExerciseInfo>() {

    override fun constructViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_exercise, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : BaseAdapter.ViewHolder<ShortExerciseInfo>(view) {

        override fun bind(item: ShortExerciseInfo) {
            itemView.title_field.text = item.title

            val muscleGroupNames = itemView.context.resources.getStringArray(R.array.muscle_groups)
            itemView.muscle_groups_field.text = item.muscleGroupsIDs.joinToString(transform = { id -> muscleGroupNames[id] })

            val resID = itemView.context.resources.getIdentifier(item.avatarID, "drawable", itemView.context.packageName)
            itemView.exercise_icon.setImageDrawable(ContextCompat.getDrawable(itemView.context, resID))
        }
    }
}

/*
    private var isFilteringMode: Boolean = false

    override fun getItemId(position: Int): Long = currentItems[position].id

    fun setList(items: List<ShortExerciseInfo>) {
        sourceItems = items
        if (isFilteringMode) {
            filterListByMuscleGroups(selectedMuscleGroupsIDs)
        } else {
            updateList(items)
        }
    }

    fun filterListByMuscleGroups(muscleGroupsIDs: IntArray) {
        isFilteringMode = true
        selectedMuscleGroupsIDs = muscleGroupsIDs
        val filteredItems =
                if (muscleGroupsIDs.isEmpty() || muscleGroupsIDs.size == muscleGroupNames.size) sourceItems
                else sourceItems.filter { item -> item.muscleGroupsIDs.any { id -> muscleGroupsIDs.contains(id) } }
        updateList(filteredItems)
    }

    fun resetFiltering() {
        isFilteringMode = false
        selectedMuscleGroupsIDs = intArrayOf()
        updateList(sourceItems)
    }
 */