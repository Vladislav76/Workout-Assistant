package com.vladislav.workoutassistant.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.db.entity.DiaryEntryEntity;
import com.vladislav.workoutassistant.databinding.FragmentDiaryBinding;
import com.vladislav.workoutassistant.ui.activities.DeleteDiaryEntriesActivity;
import com.vladislav.workoutassistant.ui.activities.WorkoutDetailsActivity;
import com.vladislav.workoutassistant.ui.adapters.DiaryEntryAdapter;
import com.vladislav.workoutassistant.ui.callbacks.DiaryEntryClickCallback;
import com.vladislav.workoutassistant.viewmodels.DiaryEntryListViewModel;
import com.vladislav.workoutassistant.viewmodels.DiaryEntryViewModel;

import java.util.List;

public class DiaryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_diary, container, false);

        mToolbar = (Toolbar) mBinding.toolbar;
        mToolbar.setTitle(R.string.diary_toolbar_title);
        ((AppCompatActivity) getActivity()).setSupportActionBar(((Toolbar) mBinding.toolbar));
        setHasOptionsMenu(true);

        mAdapter = getAdapter();
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.recyclerView.setAdapter(mAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDiaryEntryListViewModel = ViewModelProviders.of(this).get(DiaryEntryListViewModel.class);
        subscribeUI(mDiaryEntryListViewModel.getEntries());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_diary_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_diary_entry:
                Intent intent = WorkoutDetailsActivity.newIntent(getActivity(), DiaryEntryViewModel.NEW_DIARY_ENTRY_ID);
                startActivity(intent);
                return true;
            case R.id.action_delete_diary_entries:
                cleanSelectedEntriesCheckboxes();
                intent = new Intent(getActivity(), DeleteDiaryEntriesActivity.class);
                startActivityForResult(intent, DELETE_ENTRIES_REQUEST_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == DELETE_ENTRIES_REQUEST_CODE) {
                System.out.println("updating");
            }
        }
    }

    private void subscribeUI(LiveData<List<DiaryEntryEntity>> liveData) {
        liveData.observe(this, new Observer<List<DiaryEntryEntity>>() {
            @Override
            public void onChanged(@Nullable List<DiaryEntryEntity> entries) {
                if (entries != null) {
                    mAdapter.setEntryList(entries);
                    mAdapter.notifyItemRangeChanged(0, entries.size());
                }
            }
        });
    }

    private void cleanSelectedEntriesCheckboxes() {
        List<DiaryEntryEntity> entries = mDiaryEntryListViewModel.getEntries().getValue();
        for (DiaryEntryEntity entry : entries) {
            entry.setSelected(false);
        }
    }

    protected DiaryEntryAdapter getAdapter() {
        return new DiaryEntryAdapter(getActivity().getApplication(), mDiaryEntryClickCallback, false);
    }

    protected DiaryEntryListViewModel mDiaryEntryListViewModel;
    protected DiaryEntryAdapter mAdapter;
    protected FragmentDiaryBinding mBinding;
    protected Toolbar mToolbar;
    private final DiaryEntryClickCallback mDiaryEntryClickCallback = new DiaryEntryClickCallback() {
        @Override
        public void onClick(DiaryEntryViewModel model) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                Intent intent = WorkoutDetailsActivity.newIntent(getActivity(), model.getEntry().get().getId());
                startActivity(intent);
            }
        }
    };

    private int DELETE_ENTRIES_REQUEST_CODE = 76;
}
