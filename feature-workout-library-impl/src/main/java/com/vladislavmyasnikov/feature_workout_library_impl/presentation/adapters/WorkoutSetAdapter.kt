package com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vladislavmyasnikov.common.presentation.adapters.SelectableBaseAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutSet
import kotlinx.android.synthetic.main.item_workout_set.view.*
import javax.inject.Inject

//class WorkoutSetAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    private var currentItems: List<WorkoutSet> = emptyList()
//    private var sourceItems: List<WorkoutSet> = emptyList()
//    private val setsPositions = mutableListOf<Int>()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        lateinit var holder: RecyclerView.ViewHolder
//        when (viewType) {
//            SET_VIEW -> {
//                val view = inflater.inflate(R.layout.item_workout_set, parent, false)
//                holder = SetViewHolder(view, context)
//            }
//            EXERCISE_VIEW -> {
//                val view = inflater.inflate(R.layout.item_workout_exercise, parent, false)
//                holder = ExerciseViewHolder(view, context)
//            }
//        }
//        return holder
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when (getItemViewType(position)) {
//            SET_VIEW -> (holder as SetViewHolder).bind(findSetByListPosition(position))
//            EXERCISE_VIEW -> (holder as ExerciseViewHolder).bind(findExerciseByListPosition(position))
//        }
//    }
//
//    override fun getItemId(position: Int): Long = currentItems[position].id
//
//    override fun getItemViewType(position: Int): Int = if (position in setsPositions) SET_VIEW else EXERCISE_VIEW
//
//    override fun getItemCount(): Int {
//        var count = 0
//        for (set in currentItems) {
//            count += set.exercises.size + 1
//        }
//        return count
//    }
//
//    fun setList(items: List<WorkoutSet>) {
//        sourceItems = items
//        var position = 0
//        for (set in items) {
//            setsPositions.add(position)
//            position += set.exercises.size + 1
//        }
//        updateList(items)
//    }
//
//    private fun updateList(items: List<WorkoutSet>) {
//        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(currentItems, items))
//        currentItems = items
//        diffResult.dispatchUpdatesTo(this)
//
//        // TODO: setsPositions updating...
//    }
//
//    private fun findSetByListPosition(position: Int): Pair<WorkoutSet,Int> {
//        val number = setsPositions.indexOf(position)
//        return currentItems[number] to number
//    }
//
//    private fun findExerciseByListPosition(position: Int): ExerciseInfo {
//        val setIndex = setsPositions.indexOf(setsPositions.findLast { it < position }!!)
//        val setPosition = setsPositions[setIndex]
//        val setInfo = currentItems[setIndex]
//        return setInfo.exercises[position - setPosition - 1]
//    }
//
//    class SetViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
//        init {
//            itemView.set_number_button.setOnClickListener {}
//        }
//
//        fun bind(item: Pair<WorkoutSet,Int>) {
//            itemView.set_field.text = "Set ${item.second + 1}"
//        }
//    }
//
//    class ExerciseViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
//
//        fun bind(item: ExerciseInfo) {
//            itemView.title_field.text = item.title
//            itemView.reps_field.text = item.reps.toString()
//            val resID = context.resources.getIdentifier(item.avararID, "drawable", context.packageName)
//            itemView.exercise_icon.setImageDrawable(ContextCompat.getDrawable(context, resID))
//        }
//    }
//
//    companion object {
//        private const val SET_VIEW = 1
//        private const val EXERCISE_VIEW = 2
//    }
//}

class WorkoutSetAdapter @Inject constructor() : SelectableBaseAdapter<WorkoutSet>() {

    override fun constructViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_workout_set, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : SelectableBaseAdapter.ViewHolder.NonSelectableViewHolder<WorkoutSet>(view) {

        override fun bind(item: WorkoutSet) {
            itemView.set_field.text = (item.id + 1).toString()
        }
    }
}