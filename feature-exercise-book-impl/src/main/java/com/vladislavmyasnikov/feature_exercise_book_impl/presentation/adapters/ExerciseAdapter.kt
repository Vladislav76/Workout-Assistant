package com.vladislavmyasnikov.feature_exercise_book_impl.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.core_components.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.core_utils.utils.utils.DiffUtilCallback
import com.vladislavmyasnikov.feature_exercise_book_impl.R
import com.vladislavmyasnikov.feature_exercise_book_impl.domain.ShortExerciseInfo
import kotlinx.android.synthetic.main.item_exercise.view.*
import javax.inject.Inject

class ExerciseAdapter @Inject constructor(private val context: Context) : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    var callback: OnItemClickCallback? = null

    private var items: List<ShortExerciseInfo> = emptyList()

    fun updateList(_items: List<ShortExerciseInfo>) {
        val diffResult = DiffUtil.calculateDiff(DiffUtilCallback(items, _items))
        items = _items
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_exercise, parent, false)
        val holder = ViewHolder(view, context)

        holder.itemView.setOnClickListener {
            val item = items[holder.adapterPosition]
            callback?.onClick(item.id, item.title)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {

        fun bind(item: ShortExerciseInfo) {
            itemView.title_field.text = item.title
        }
    }
}