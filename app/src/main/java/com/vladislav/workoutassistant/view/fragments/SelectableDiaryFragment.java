package com.vladislav.workoutassistant.view.fragments;

import android.os.Bundle;
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

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class SelectableDiaryFragment extends DiaryFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        if (savedInstanceState == null) {
            for (DiaryEntry entry : Diary.getDiary().getEntries()) {
                entry.setSelected(false);
            }
        }
        else {
            mSelectedEntriesCount = savedInstanceState.getInt(EXTRA_SELECTED_ENTRIES_COUNT);
        }
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        updateToolbar();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(EXTRA_SELECTED_ENTRIES_COUNT, mSelectedEntriesCount);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_selectable_diary_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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

    @Override
    protected DiaryEntryAdapter getAdapter(List<DiaryEntry> entries) {
        return new DiaryEntryAdapter(entries);
    }

    private void updateToolbar() {
        mToolbar.setTitle(Integer.toString(mSelectedEntriesCount));
    }

    protected class DiaryEntryHolder extends DiaryFragment.DiaryEntryHolder {
        DiaryEntryHolder(ListItemDiaryEntryBinding binding) {
            super(binding);
            mBinding.selectionCheckbox.setVisibility(View.VISIBLE);
        }

        @Override
        public void onClick(View view) {
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
    }

    protected class DiaryEntryAdapter extends DiaryFragment.DiaryEntryAdapter {
        DiaryEntryAdapter(List<DiaryEntry> entries) {
            super(entries);
        }

        @Override
        protected DiaryEntryHolder getHolder(ListItemDiaryEntryBinding binding) {
            return new DiaryEntryHolder(binding);
        }
    }

    private int mSelectedEntriesCount;

    private static final String EXTRA_SELECTED_ENTRIES_COUNT = "extra_selected_entries_count";
}
