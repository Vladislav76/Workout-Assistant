package com.vladislav.workoutassistant.diary.adapters;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.core.DiffUtilCallback;
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

    private List<DiaryEntry> mEntries;
    private boolean mIsSelectedMode;
    private Application mApplication;
    private DiaryEntryClickCallback mCallback;

    public DiaryEntryAdapter(Application application, @Nullable DiaryEntryClickCallback callback, boolean isSelectedMode) {
        mCallback = callback;
        mIsSelectedMode = isSelectedMode;
        mApplication = application;
//        setHasStableIds(true);
    }

    public void updateList(List<DiaryEntry> entries) {
        if (mEntries == null) {
            mEntries = entries;
            notifyItemRangeInserted(0, entries.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallback(mEntries, entries));
            mEntries = entries;
            diffResult.dispatchUpdatesTo(this);
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
        holder.mViewModel.setEntry(mEntries.get(position));
        holder.mBinding.idField.setText(Integer.toString(position + 1));
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mEntries == null ? 0 : mEntries.size();
    }

    @Override
    public long getItemId(int position) {
        return mEntries.get(position).getId();
    }

    class DiaryEntryViewHolder extends RecyclerView.ViewHolder {
        private ItemDiaryEntryBinding mBinding;
        private DiaryEntryViewModel mViewModel;

        DiaryEntryViewHolder(ItemDiaryEntryBinding binding) {
            super(binding.getRoot());

            mBinding = binding;
            mViewModel = new DiaryEntryViewModel(mApplication);
            mBinding.setViewModel(mViewModel);
            if (mIsSelectedMode) {
                mBinding.selectionCheckbox.setVisibility(View.VISIBLE);
            } else {
                mBinding.selectionCheckbox.setVisibility(View.GONE);
            }
        }
    }
}