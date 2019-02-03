package com.vladislav.workoutassistant.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.Diary;
import com.vladislav.workoutassistant.data.DiaryEntry;
import com.vladislav.workoutassistant.databinding.ListItemDiaryEntryBinding;
import com.vladislav.workoutassistant.view.activities.WorkoutDetailsActivity;
import com.vladislav.workoutassistant.viewmodel.DiaryEntryViewModel;

import java.util.List;

public class DiaryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary, container, false);

        mDiaryRecyclerView = view.findViewById(R.id.diary_recycler_view);
        mDiaryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        if (mAdapter == null) {
            Diary diary = Diary.getDiary();
            mAdapter = new DiaryEntryAdapter(diary.getEntries());
            mDiaryRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class DiaryEntryHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private ListItemDiaryEntryBinding mBinding;

        public DiaryEntryHolder(ListItemDiaryEntryBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(new DiaryEntryViewModel());
            itemView.setOnClickListener(this);
        }

        public void bind(DiaryEntry diaryEntry) {
            mBinding.getViewModel().setDiaryEntry(diaryEntry);
            mBinding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            Intent intent = WorkoutDetailsActivity.newIntent(getActivity(),
                    mBinding.getViewModel().getDiaryEntry().getId());
            startActivity(intent);
        }
    }

    private class DiaryEntryAdapter extends RecyclerView.Adapter<DiaryEntryHolder> {
        private List<DiaryEntry> mEntries;

        public DiaryEntryAdapter(List<DiaryEntry> entries) {
            mEntries = entries;
        }

        @Override
        public int getItemCount() {
            return mEntries.size();
        }

        @Override
        public DiaryEntryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemDiaryEntryBinding binding = DataBindingUtil
                    .inflate(inflater, R.layout.list_item_diary_entry, parent, false);
            return new DiaryEntryHolder(binding);
        }

        @Override
        public void onBindViewHolder(DiaryEntryHolder holder, int position) {
            holder.bind(mEntries.get(position));
        }
    }

    private DiaryEntryAdapter mAdapter;
    private RecyclerView mDiaryRecyclerView;
}
