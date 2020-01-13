package com.vladislavmyasnikov.common.experimental

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.common.interfaces.Identifiable
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.utils.DiffUtilCallback

abstract class BaseAdapter<T : Identifiable<T>> : RecyclerView.Adapter<BaseAdapter.ViewHolder<T>>() {

    var callback: OnItemClickCallback? = null

    private var currentItems: List<T> = emptyList()
    private var sourceItems: List<T> = emptyList()

    override fun getItemCount() = currentItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val holder = constructViewHolder(parent, viewType)

        holder.itemView.setOnClickListener {
            val item = currentItems[holder.adapterPosition]
            callback?.onClick(holder.adapterPosition.toLong(), "Fake title")
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bind(currentItems[position])
    }

    fun setList(items: List<T>) {
        sourceItems = items
        updateList(items)
    }

    protected abstract fun constructViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T>

    private fun updateList(items: List<T>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(currentItems, items))
        currentItems = items
        diffResult.dispatchUpdatesTo(this)
    }

    abstract class ViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

        abstract fun bind(item: T)
    }
}

/*

    var selectedMuscleGroupsIDs: IntArray = intArrayOf()
        private set

    private val muscleGroupNames: Array<String> = context.resources.getStringArray(R.array.muscle_groups)
    private var isFilteringMode: Boolean = false

//    override fun getItemId(position: Int): Long = currentItems[position].id


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