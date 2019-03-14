package com.vladislav.workoutassistant.workouts.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.db.entity.ProgramCategoryEntity;
import com.vladislav.workoutassistant.databinding.ItemProgramCategoryBinding;
import com.vladislav.workoutassistant.core.callbacks.ItemClickCallback;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ProgramCategoryAdapter extends RecyclerView.Adapter<ProgramCategoryAdapter.ProgramCategoryViewHolder> {

    public ProgramCategoryAdapter(ItemClickCallback callback) {
        mCallback = callback;
    }

    public void setList(final List<ProgramCategoryEntity> list) {
        if (mProgramCategoryList == null) {
            mProgramCategoryList = list;
            notifyItemRangeChanged(0, list.size());
        }
        else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mProgramCategoryList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mProgramCategoryList.get(oldItemPosition).getId() == list.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    ProgramCategoryEntity oldItem = mProgramCategoryList.get(oldItemPosition);
                    ProgramCategoryEntity newItem = list.get(newItemPosition);
                    return oldItem.getName().equals(newItem.getName());
                }
            });
            mProgramCategoryList = list;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    @NonNull
    public ProgramCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemProgramCategoryBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_program_category, parent, false);
        binding.setCallback(mCallback);
        return new ProgramCategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramCategoryViewHolder holder, int position) {
        ProgramCategoryEntity category = mProgramCategoryList.get(position);
        holder.mBinding.setId(category.getId());
        holder.mBinding.setName(category.getName());
    }

    @Override
    public int getItemCount() {
        return mProgramCategoryList == null ? 0 : mProgramCategoryList.size();
    }

    class ProgramCategoryViewHolder extends RecyclerView.ViewHolder {
        private ItemProgramCategoryBinding mBinding;

        ProgramCategoryViewHolder(ItemProgramCategoryBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private List<ProgramCategoryEntity> mProgramCategoryList;
    private ItemClickCallback mCallback;
}
