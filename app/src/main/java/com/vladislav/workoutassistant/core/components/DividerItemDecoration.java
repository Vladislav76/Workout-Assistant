package com.vladislav.workoutassistant.core.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.vladislav.workoutassistant.R;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int mDividerHeight;

    public DividerItemDecoration(Context context, int resId, int dividerHeight) {
        mDivider = ContextCompat.getDrawable(context, resId);
        mDividerHeight = dividerHeight;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (view.getTag(R.id.black_horizontal_divider) != null) {
            outRect.set(0, mDividerHeight * 2, 0, mDividerHeight);
        }
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < parent.getChildCount(); i++) {
            View view = parent.getChildAt(i);
            int adapterPosition = parent.getChildAdapterPosition(view);
            Object tag = view.getTag(R.id.black_horizontal_divider);
            if (tag != null) {
                if (adapterPosition != 0) {
                    int bottom = view.getTop();
                    mDivider.setBounds(left, bottom - mDividerHeight, right, bottom);
                    mDivider.draw(canvas);
                }
            }
        }
    }
}
