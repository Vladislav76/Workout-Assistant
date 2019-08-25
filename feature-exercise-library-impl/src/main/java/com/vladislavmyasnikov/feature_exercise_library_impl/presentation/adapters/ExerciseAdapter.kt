package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.vladislavmyasnikov.core_components.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.core_utils.utils.utils.DiffUtilCallback
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ShortExerciseInfo
import kotlinx.android.synthetic.main.item_exercise.view.*
import javax.inject.Inject

class ExerciseAdapter @Inject constructor(private val context: Context) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    var callback: OnItemClickCallback? = null
    var selectedMuscleGroupsIDs: IntArray = intArrayOf()
        private set

    private var currentItems: List<ShortExerciseInfo> = emptyList()
    private var sourceItems: List<ShortExerciseInfo> = emptyList()
    private val muscleGroupNames: Array<String> = context.resources.getStringArray(R.array.muscle_groups)
    private var isFilteringMode: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_exercise, parent, false)
        val holder = ViewHolder(view, muscleGroupNames, context)

        holder.itemView.setOnClickListener {
            val item = currentItems[holder.adapterPosition]
            callback?.onClick(item.id, item.title)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentItems[position])
    }

    override fun getItemCount(): Int = currentItems.size

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

    private fun updateList(items: List<ShortExerciseInfo>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(currentItems, items))
        currentItems = items
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(view: View, private val muscleGroupNames: Array<String>, context: Context) : RecyclerView.ViewHolder(view) {

        init {
            for (i in 0 until muscleGroupNames.size) {
                val tag = Chip(context).apply {
                    visibility = View.GONE
                    isClickable = false
                }
                itemView.muscle_groups_tags.addView(tag)
            }
        }

        fun bind(item: ShortExerciseInfo) {
            itemView.title_field.text = item.title
            for ((position, id) in item.muscleGroupsIDs.withIndex()) {
                val tag = itemView.muscle_groups_tags.getChildAt(position) as Chip
                tag.text = muscleGroupNames[id]
                tag.visibility = View.VISIBLE
            }
            for (i in item.muscleGroupsIDs.size until muscleGroupNames.size) {
                val tag = itemView.muscle_groups_tags.getChildAt(i) as Chip
                tag.visibility = View.GONE
            }
        }
    }
}