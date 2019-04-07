package com.vladislav.workoutassistant.ui.workouts.adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.ui.main.interfaces.OnItemClickCallback;
import com.vladislav.workoutassistant.data.models.NamedObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<NamedObject> mCategories;
    private OnItemClickCallback mCallback;
    private int mSelectedItemPosition;

    public CategoryAdapter(List<NamedObject> categories, OnItemClickCallback callback) {
        mCategories = categories;
        mCallback = callback;
    }

    public void setItemPosition(int position) {
        //notifyItemChanged(mSelectedItemPosition);
        mSelectedItemPosition = position;
        notifyItemChanged(mSelectedItemPosition);
    }

    @Override
    @NonNull
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(mCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategories == null ? 0 : mCategories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleView;
        private NamedObject mCategory;

        CategoryViewHolder(View view) {
            super(view);
            mTitleView = view.findViewById(R.id.category_item_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() != mSelectedItemPosition) {
                notifyItemChanged(mSelectedItemPosition);
                mSelectedItemPosition = getAdapterPosition();
                notifyItemChanged(mSelectedItemPosition);
            }
            mCallback.onClick(mCategory.getId(), mCategory.getName());
        }

        void bind(NamedObject category) {
            if (getAdapterPosition() == mSelectedItemPosition) {
                mTitleView.setPaintFlags(mTitleView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            } else {
                mTitleView.setPaintFlags(mTitleView.getPaintFlags() & Paint.LINEAR_TEXT_FLAG);
            }
            mCategory = category;
            mTitleView.setText(category.getName());
        }
    }
}
