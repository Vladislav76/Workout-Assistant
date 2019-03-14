package com.vladislav.workoutassistant.workouts.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.db.entity.Category;
import com.vladislav.workoutassistant.core.callbacks.ItemClickCallback;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> mCategories;
    private ItemClickCallback mCallback;

    public CategoryAdapter(List<Category> categories, ItemClickCallback callback) {
        mCategories = categories;
        mCallback = callback;
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

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleView;
        private Category mCategory;

        CategoryViewHolder(View view) {
            super(view);
            mTitleView = view.findViewById(R.id.category_item_title);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onClick(mCategory.getId(), mCategory.getName());
                }
            });
        }

        void bind(Category category) {
            mCategory = category;
            mTitleView.setText(category.getName());
        }
    }
}
