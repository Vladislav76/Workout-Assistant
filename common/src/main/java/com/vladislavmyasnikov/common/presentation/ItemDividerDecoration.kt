package com.vladislavmyasnikov.common.presentation

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDividerDecoration(private val horizontalDividerThickness: Int, private val verticalDividerThickness: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(verticalDividerThickness, horizontalDividerThickness, verticalDividerThickness, horizontalDividerThickness)
    }
}