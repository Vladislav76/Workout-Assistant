package com.vladislav.workoutassistant.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.model.AbbreviatedDiaryEntry;
import com.vladislav.workoutassistant.ui.adapters.DiaryEntryAdapter;
import com.vladislav.workoutassistant.ui.callbacks.DiaryEntryClickCallback;
import com.vladislav.workoutassistant.viewmodels.DiaryEntryViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SelectableDiaryFragment extends DiaryFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        if (savedInstanceState != null) {
            mSelectedEntriesCount = savedInstanceState.getInt(EXTRA_SELECTED_ENTRIES_COUNT);
        }

        updateToolbar(Integer.toString(mSelectedEntriesCount));

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putInt(EXTRA_SELECTED_ENTRIES_COUNT, mSelectedEntriesCount);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.selectable_diary_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_selected_diary_entries_action:
                deleteEntries();
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteEntries() {
        if (mSelectedEntriesCount > 0) {
            List<Integer> ids = new ArrayList<>(mSelectedEntriesCount);
            int k = 0;
            for (AbbreviatedDiaryEntry entry : mDiaryEntryListViewModel.getEntries().getValue()) {
                if (entry.isSelected()) {
                    ids.add(entry.getId());
                    k++;
                    if (k == mSelectedEntriesCount) {
                        break;
                    }
                }
            }
            mDiaryEntryListViewModel.deleteEntriesById(ids);
            getActivity().setResult(Activity.RESULT_OK, null);
        }
    }

    protected DiaryEntryAdapter getAdapter() {
        return new DiaryEntryAdapter(getActivity().getApplication(), mDiaryEntryClickCallback, true);
    }

    public static SelectableDiaryFragment newInstance() {
        return new SelectableDiaryFragment();
    }

    private int mSelectedEntriesCount;
    private final DiaryEntryClickCallback mDiaryEntryClickCallback = new DiaryEntryClickCallback() {
        @Override
        public void onClick(DiaryEntryViewModel model, String name) {
            boolean wasSelected = model.getEntry().get().isSelected();
            model.setSelected(!wasSelected);
            if (wasSelected) {
                mSelectedEntriesCount--;
            }
            else {
                mSelectedEntriesCount++;
            }
            updateToolbar(Integer.toString(mSelectedEntriesCount));
        }
    };

    private static final String EXTRA_SELECTED_ENTRIES_COUNT = "extra_selected_entries_count";
}
