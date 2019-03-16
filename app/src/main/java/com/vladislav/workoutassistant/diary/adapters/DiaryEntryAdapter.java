package com.vladislav.workoutassistant.diary.adapters;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.db.entity.DiaryEntry;
import com.vladislav.workoutassistant.data.model.AbbreviatedDiaryEntry;
import com.vladislav.workoutassistant.databinding.ItemDiaryEntryBinding;
import com.vladislav.workoutassistant.core.callbacks.DiaryEntryClickCallback;
import com.vladislav.workoutassistant.diary.viewmodels.DiaryEntryViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class DiaryEntryAdapter extends RecyclerView.Adapter<DiaryEntryAdapter.DiaryEntryViewHolder> {

    private List<DiaryEntry> mEntryList;
    private boolean mIsSelectedMode;
    private Application mApplication;
    private DiaryEntryClickCallback mCallback;

    public DiaryEntryAdapter(Application application, @Nullable DiaryEntryClickCallback callback, boolean isSelectedMode) {
        mCallback = callback;
        mIsSelectedMode = isSelectedMode;
        mApplication = application;
        setHasStableIds(true);
    }

    public void setEntryList(final List<DiaryEntry> entryList) {
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

    @NonNull
    @Override
    public DiaryEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemDiaryEntryBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.item_diary_entry, parent, false);
        binding.setCallback(mCallback);
        return new DiaryEntryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryEntryViewHolder holder, int position) {
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
        private ItemDiaryEntryBinding mBinding;
        private DiaryEntryViewModel mViewModel;

        DiaryEntryViewHolder(ItemDiaryEntryBinding binding) {
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
}