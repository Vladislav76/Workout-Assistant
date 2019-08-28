package com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.core_components.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.core_utils.utils.utils.DiffUtilCallback
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.ShortWorkoutInfo
import kotlinx.android.synthetic.main.item_workout.view.*
import javax.inject.Inject

class WorkoutAdapter @Inject constructor(private val context: Context) : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {

    var callback: OnItemClickCallback? = null

    private var currentItems: List<ShortWorkoutInfo> = emptyList()
    private var sourceItems: List<ShortWorkoutInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_workout, parent, false)
        val holder = ViewHolder(view, context)

        holder.itemView.setOnClickListener {
            val item = currentItems[holder.adapterPosition]
            callback?.onClick(item.id, item.title)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentItems[position])
    }

    override fun getItemId(position: Int): Long = currentItems[position].id

    override fun getItemCount(): Int = currentItems.size

    fun setList(items: List<ShortWorkoutInfo>) {
        sourceItems = items
        updateList(items)
    }

    private fun updateList(items: List<ShortWorkoutInfo>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(currentItems, items))
        currentItems = items
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {

        fun bind(item: ShortWorkoutInfo) {
            itemView.title_field.text = item.title
        }
    }
}