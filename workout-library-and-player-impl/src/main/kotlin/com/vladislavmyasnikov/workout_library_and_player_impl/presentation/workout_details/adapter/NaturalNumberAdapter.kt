package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.adapter

import android.content.Context
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes

class NaturalNumberAdapter(context: Context, @LayoutRes layoutRes: Int, @StringRes private val stringRes: Int) : ArrayAdapter<String>(context, layoutRes) {

    var lastNumber: Int = 0
        set(value) {
            if (value >= 0 && value != field) {
                field = value
                this.notifyDataSetChanged()
            }
        }

    override fun getItem(position: Int): String? = "${context.resources.getString(stringRes)} ${position + 1}"

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = lastNumber
}

