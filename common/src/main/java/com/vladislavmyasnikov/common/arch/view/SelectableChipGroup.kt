package com.vladislavmyasnikov.common.arch.view

import android.content.Context
import android.util.AttributeSet
import android.widget.CompoundButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlin.math.min

class SelectableChipGroup : ChipGroup {

    fun interface OnChipCheckedListener {
        fun onChipChecked(position: Int, isChecked: Boolean)
    }

    private var onChipCheckedListener: OnChipCheckedListener? = null

    private val onCheckedChangeListener = CompoundButton.OnCheckedChangeListener { view, isChecked ->
        val position = indexOfChild(view)
        onChipCheckedListener?.onChipChecked(position, isChecked)
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(context, attributeSet, defStyle)

    fun setItems(items: List<Pair<String,Boolean>>) {
        val k = min(childCount, items.size)
        for (i in 0 until k) {
            (getChildAt(i) as Chip).apply {
                text = items[i].first
                isChecked = items[i].second
            }
        }
        if (childCount > items.size) {
            removeViews(items.size, childCount - items.size)
        }
        if (childCount < items.size) {
            for (i in k until items.size) {
                addView(
                        Chip(context).apply {
                            isCheckable = true
                            text = items[i].first
                            isChecked = items[i].second
                            setOnCheckedChangeListener(onCheckedChangeListener)
                        }
                )
            }
        }
    }

    fun setOnChipCheckedListener(listener: OnChipCheckedListener) {
        onChipCheckedListener = listener
    }
}