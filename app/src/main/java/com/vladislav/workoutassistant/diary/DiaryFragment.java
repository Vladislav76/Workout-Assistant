package com.vladislav.workoutassistant.diary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.db.entity.DiaryEntry;
import com.vladislav.workoutassistant.core.GeneralFragment;
import com.vladislav.workoutassistant.diary.adapters.DiaryEntryAdapter;
import com.vladislav.workoutassistant.core.callbacks.DiaryEntryClickCallback;
import com.vladislav.workoutassistant.diary.viewmodels.DiaryEntryListViewModel;
import com.vladislav.workoutassistant.diary.viewmodels.DiaryEntryViewModel;

import java.util.List;

public class DiaryFragment extends GeneralFragment {

    private int DELETE_ENTRIES_REQUEST_CODE = 76;

    protected DiaryEntryListViewModel mDiaryEntryListViewModel;
    protected DiaryEntryAdapter mAdapter;
    private DiaryEntryClickCallback mDiaryEntryClickCallback = new DiaryEntryClickCallback() {
        @Override
        public void onClick(DiaryEntryViewModel model, String name) {
            DiaryEntryDetailsFragment fragment = DiaryEntryDetailsFragment.newInstance(model.getEntry().get().getId(), name);
            mFragmentListener.addFragmentOnTop(fragment);
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        updateToolbar(R.string.diary_toolbar_title);
        setHasOptionsMenu(true);

        RecyclerView recyclerView = new RecyclerView(getActivity());

        if (mAdapter == null) {
            mAdapter = getAdapter();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);

        mDiaryEntryListViewModel = ViewModelProviders.of(this).get(DiaryEntryListViewModel.class);
        mDiaryEntryListViewModel.getEntries().observe(this, new Observer<List<DiaryEntry>>() {
            @Override
            public void onChanged(@Nullable List<DiaryEntry> entries) {
                if (entries != null) {
                    mAdapter.setEntryList(entries);
                    mAdapter.notifyItemRangeChanged(0, entries.size());
                }
            }
        });

        return recyclerView;
    }


//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mDiaryEntryListViewModel = ViewModelProviders.of(this).get(DiaryEntryListViewModel.class);
//        mDiaryEntryListViewModel.getEntries().observe(this, new Observer<List<DiaryEntry>>() {
//            @Override
//            public void onChanged(@Nullable List<DiaryEntry> entries) {
//                if (entries != null) {
//                    mAdapter.setEntryList(entries);
//                    mAdapter.notifyItemRangeChanged(0, entries.size());
//                }
//            }
//        });
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.diary_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_diary_entry_action:
                Fragment fragment = DiaryEntryDetailsFragment.newInstance(DiaryEntryViewModel.NEW_DIARY_ENTRY_ID, null);
                mFragmentListener.addFragmentOnTop(fragment);
                return true;
            case R.id.delete_diary_entries_action:
                cleanSelectedEntriesCheckboxes();
                fragment = SelectableDiaryFragment.newInstance();
                mFragmentListener.addFragmentOnTop(fragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == DELETE_ENTRIES_REQUEST_CODE) {
//                System.out.println("updating");
//            }
//        }
//    }

    private void cleanSelectedEntriesCheckboxes() {
        List<DiaryEntry> entries = mDiaryEntryListViewModel.getEntries().getValue();
        for (DiaryEntry entry : entries) {
            entry.setSelected(false);
        }
    }

    protected DiaryEntryAdapter getAdapter() {
        return new DiaryEntryAdapter(getActivity().getApplication(), mDiaryEntryClickCallback, false);
    }

    public static DiaryFragment newInstance() {
        return new DiaryFragment();
    }
}
