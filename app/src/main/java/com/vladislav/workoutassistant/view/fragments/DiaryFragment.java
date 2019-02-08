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
import com.vladislav.workoutassistant.view.activities.DeleteDiaryEntriesActivity;
import com.vladislav.workoutassistant.view.activities.WorkoutDetailsActivity;
import com.vladislav.workoutassistant.viewmodel.DiaryEntryViewModel;

import java.util.ArrayList;
import java.util.List;

public class DiaryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary, container, false);

        mToolbar = view.findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.diary_toolbar_title);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        setHasOptionsMenu(true);

        mVisibleSelectionCheckbox = getArguments().getBoolean(ARG_VISIBLE_SELECTION_CHECKBOX);
        if (savedInstanceState == null) {
            for (DiaryEntry entry : Diary.getDiary().getEntries()) {
                entry.setSelected(false);
            }
        }
        else {
            mSelectedEntriesCount = savedInstanceState.getInt(EXTRA_SELECTED_ENTRIES_COUNT);
        }

        if (mVisibleSelectionCheckbox) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            updateToolbar();
        }

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
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(EXTRA_SELECTED_ENTRIES_COUNT, mSelectedEntriesCount);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        int id;
        if (mVisibleSelectionCheckbox) {
            id = R.menu.fragment_selectable_diary_list;
        }
        else {
            id = R.menu.fragment_diary_list;
        }
        inflater.inflate(id, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_diary_entry:
                Intent intent = WorkoutDetailsActivity.newIntent(getActivity(),
                        Diary.NEW_TEMP_DIARY_ENTRY);
                startActivity(intent);
                return true;
            case R.id.action_delete_diary_entries:
                intent = new Intent(getActivity(), DeleteDiaryEntriesActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_delete_selected_diary_entries:
                Diary diary = Diary.getDiary();
                int i = 0;
                while (i < diary.getEntries().size()) {
                    DiaryEntry entry = diary.getEntries().get(i);
                    if (entry.isSelected()) {
                        diary.removeEntry(entry);
                    }
                    else {
                        i++;
                    }
                }
                getActivity().finish();
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

    private void updateToolbar() {
        mToolbar.setTitle(Integer.toString(mSelectedEntriesCount));
    }

    private class DiaryEntryHolder extends RecyclerView.ViewHolder {
        private ListItemDiaryEntryBinding mBinding;

        public DiaryEntryHolder(ListItemDiaryEntryBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(new DiaryEntryViewModel());

            View.OnClickListener listener;
            if (mVisibleSelectionCheckbox) {
                listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean wasSelected = mBinding.getViewModel().isSelected();
                        mBinding.getViewModel().setSelected(!wasSelected);
                        if (wasSelected) {
                            mSelectedEntriesCount--;
                        }
                        else {
                            mSelectedEntriesCount++;
                        }
                        updateToolbar();
                    }
                };
            }
            else {
                mBinding.selectionCheckbox.setVisibility(View.GONE);
                listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = WorkoutDetailsActivity.newIntent(getActivity(),
                                mBinding.getViewModel().getDiaryEntry().getId());
                        startActivity(intent);
                    }
                };
            }
            itemView.setOnClickListener(listener);
        }

        public void bind(DiaryEntry diaryEntry) {
            mBinding.getViewModel().setDiaryEntry(diaryEntry);
            mBinding.executePendingBindings();
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

    public static DiaryFragment newInstance(boolean isVisibleSelectionCheckbox) {
        Bundle args = new Bundle();
        args.putBoolean(ARG_VISIBLE_SELECTION_CHECKBOX, isVisibleSelectionCheckbox);

        DiaryFragment fragment = new DiaryFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private DiaryEntryAdapter mAdapter;
    private RecyclerView mDiaryRecyclerView;
    private Toolbar mToolbar;
    private boolean mVisibleSelectionCheckbox;
    private int mSelectedEntriesCount;

    private static final String EXTRA_SELECTED_ENTRIES_COUNT = "extra_selected_entries_count";
    private static final String ARG_VISIBLE_SELECTION_CHECKBOX = "arg_visible_selection_checkbox";
}
