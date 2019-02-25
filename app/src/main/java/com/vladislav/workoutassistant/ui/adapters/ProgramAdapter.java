package com.vladislav.workoutassistant.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.db.entity.ProgramEntity;
import com.vladislav.workoutassistant.databinding.ProgramItemBinding;
import com.vladislav.workoutassistant.ui.callbacks.ItemClickCallback;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder> {

    public ProgramAdapter(ItemClickCallback callback) {
        mCallback = callback;
    }

    public void setList(final List<ProgramEntity> list) {
        if (mProgramList == null) {
            mProgramList = list;
            notifyItemRangeChanged(0, list.size());
        }
        else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mProgramList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mProgramList.get(oldItemPosition).getId() == list.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    ProgramEntity oldItem = mProgramList.get(oldItemPosition);
                    ProgramEntity newItem = list.get(newItemPosition);
                    return oldItem.getName().equals(newItem.getName());
                }
            });
            mProgramList = list;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ProgramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ProgramItemBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.program_item, parent, false);
        binding.setCallback(mCallback);
        return new ProgramViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProgramViewHolder holder, int position) {
        ProgramEntity category = mProgramList.get(position);
        holder.mBinding.setId(category.getId());
        holder.mBinding.nameField.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return mProgramList == null ? 0 : mProgramList.size();
    }

    class ProgramViewHolder extends RecyclerView.ViewHolder {
        private ProgramItemBinding mBinding;

        ProgramViewHolder(ProgramItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private List<ProgramEntity> mProgramList;
    private ItemClickCallback mCallback;
}
