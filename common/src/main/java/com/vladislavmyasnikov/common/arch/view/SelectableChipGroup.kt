package com.vladislavmyasnikov.common.arch.view

import android.content.Context
import android.util.AttributeSet
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.vladislavmyasnikov.common.R
import kotlin.math.min

class SelectableChipGroup : ChipGroup {

    fun interface OnChipCheckedListener {
        fun onChipChecked(position: Int, isChecked: Boolean)
    }

    private var onChipCheckedListener: OnChipCheckedListener? = null
    private var isSelectable = false

    private val onCheckedChangeListener = CompoundButton.OnCheckedChangeListener { view, isChecked ->
        val position = indexOfChild(view)
        onChipCheckedListener?.onChipChecked(position, isChecked)
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) { applyAttrs(attributeSet) }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(context, attributeSet, defStyle) { applyAttrs(attributeSet) }

    fun setItems(items: List<String>, isSelected: List<Boolean>? = null) {
        val k = min(childCount, items.size)
        for (i in 0 until k) {
            (getChildAt(i) as Chip).apply {
                text = items[i]
                isChecked = isSelected?.get(i) ?: false
            }
        }
        if (childCount > items.size) {
            removeViews(items.size, childCount - items.size)
        }
        if (childCount < items.size) {
            for (i in k until items.size) {
                addView(
                        Chip(context).apply {
                            isCheckable = isSelectable
                            text = items[i]
                            isChecked = isSelected?.get(i) ?: false
                            chipBackgroundColor = ContextCompat.getColorStateList(context, R.color.tag_state_list)
                            checkedIcon = null
                            setOnCheckedChangeListener(onCheckedChangeListener)
                        }
                )
            }
        }
    }

    fun setOnChipCheckedListener(listener: OnChipCheckedListener) {
        onChipCheckedListener = listener
    }

    fun hasItems(): Boolean {
        return childCount > 0
    }

    private fun applyAttrs(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.SelectableChipGroup,
                0, 0).apply {
                    try {
                        isSelectable = getBoolean(R.styleable.SelectableChipGroup_isSelectable, false)
                    } finally {
                        recycle()
                    }
                }
    }
}