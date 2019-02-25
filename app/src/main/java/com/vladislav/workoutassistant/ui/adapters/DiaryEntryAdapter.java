package com.vladislav.workoutassistant.ui.adapters;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.db.entity.DiaryEntryEntity;
import com.vladislav.workoutassistant.data.model.AbbreviatedDiaryEntry;
import com.vladislav.workoutassistant.databinding.DiaryEntryItemBinding;
import com.vladislav.workoutassistant.ui.callbacks.DiaryEntryClickCallback;
import com.vladislav.workoutassistant.viewmodels.DiaryEntryViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class DiaryEntryAdapter extends RecyclerView.Adapter<DiaryEntryAdapter.DiaryEntryViewHolder> {

    public DiaryEntryAdapter(Application application, @Nullable DiaryEntryClickCallback clickCallback, boolean isSelectedMode) {
        mDiaryEntryClickCallback = clickCallback;
        mIsSelectedMode = isSelectedMode;
        mApplication = application;
        setHasStableIds(true);
    }

    public void setEntryList(final List<DiaryEntryEntity> entryList) {
        if (mEntryList == null) {
            mEntryList = entryList;
            notifyItemRangeChanged(0, entryList.size());
        }
        else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mEntryList.size();
                }

                @Override
                public int getNewListSize() {
                    return entryList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mEntryList.get(oldItemPosition).getId() ==
                            entryList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    AbbreviatedDiaryEntry oldEntry = mEntryList.get(oldItemPosition);
                    AbbreviatedDiaryEntry newEntry = entryList.get(newItemPosition);
                    return oldEntry.getDate().equals(newEntry.getDate())
                            && oldEntry.getTitle().equals(newEntry.getTitle())
                            && oldEntry.getDuration().equals(newEntry.getDuration())
                            && oldEntry.isSelected() == newEntry.isSelected();
                }
            });
            mEntryList = entryList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public DiaryEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DiaryEntryItemBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.diary_entry_item, parent, false);
        binding.setCallback(mDiaryEntryClickCallback);
        return new DiaryEntryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DiaryEntryViewHolder holder, int position) {
        holder.mViewModel.setEntry(mEntryList.get(position));
        holder.mBinding.idField.setText(Integer.toString(position + 1));
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mEntryList == null ? 0 : mEntryList.size();
    }

    @Override
    public long getItemId(int position) {
        return mEntryList.get(position).getId();
    }

    class DiaryEntryViewHolder extends RecyclerView.ViewHolder {
        private DiaryEntryItemBinding mBinding;
        private DiaryEntryViewModel mViewModel;

        DiaryEntryViewHolder(DiaryEntryItemBinding binding) {
            super(binding.getRoot());
            mViewModel = new DiaryEntryViewModel(mApplication);
            mBinding = binding;
            mBinding.setViewModel(mViewModel);
            if (mIsSelectedMode) {
                mBinding.selectionCheckbox.setVisibility(View.VISIBLE);
            }
            else {
                mBinding.selectionCheckbox.setVisibility(View.GONE);
            }
        }
    }

    private List<DiaryEntryEntity> mEntryList;
    private boolean mIsSelectedMode;
    private Application mApplication;

    @Nullable
    private final DiaryEntryClickCallback mDiaryEntryClickCallback;
}