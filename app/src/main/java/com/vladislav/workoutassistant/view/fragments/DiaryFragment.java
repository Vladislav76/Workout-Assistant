package com.vladislav.workoutassistant.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        mDiaryRecyclerView = view.findViewById(R.id.recycler_view);
        mDiaryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_diary_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_diary_entry:
                Intent intent = WorkoutDetailsActivity.newIntent(getActivity(),
                        Diary.NEW_TEMP_DIARY_ENTRY);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
