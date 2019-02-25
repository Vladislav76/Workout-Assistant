package com.vladislav.workoutassistant.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.vladislav.workoutassistant.data.db.entity.DiaryEntryEntity;
import com.vladislav.workoutassistant.ui.adapters.DiaryEntryAdapter;
import com.vladislav.workoutassistant.ui.callbacks.DiaryEntryClickCallback;
import com.vladislav.workoutassistant.ui.callbacks.OnFragmentListener;
import com.vladislav.workoutassistant.viewmodels.DiaryEntriesViewModel;
import com.vladislav.workoutassistant.viewmodels.DiaryEntryViewModel;

import java.util.List;

public class DiaryFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentListener) {
            mFragmentListener = (OnFragmentListener) context;
        }
        else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView view = new RecyclerView(getActivity());

        mAdapter = getAdapter();
        view.setLayoutManager(new LinearLayoutManager(getActivity()));
        view.setAdapter(mAdapter);
        mDiaryEntryListViewModel = ViewModelProviders.of(this).get(DiaryEntriesViewModel.class);
        mDiaryEntryListViewModel.getEntries().observe(this, new Observer<List<DiaryEntryEntity>>() {
            @Override
            public void onChanged(@Nullable List<DiaryEntryEntity> entries) {
                if (entries != null) {
                    mAdapter.setEntryList(entries);
                    mAdapter.notifyItemRangeChanged(0, entries.size());
                }
            }
        });

        mToolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        mToolbar.setTitle(R.string.diary_toolbar_title);
        mToolbar.setDisplayHomeAsUpEnabled(false);
        setHasOptionsMenu(true);

        return view;
    }


//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mDiaryEntryListViewModel = ViewModelProviders.of(this).get(DiaryEntriesViewModel.class);
//        mDiaryEntryListViewModel.getEntries().observe(this, new Observer<List<DiaryEntryEntity>>() {
//            @Override
//            public void onChanged(@Nullable List<DiaryEntryEntity> entries) {
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
                Fragment fragment = WorkoutDetailsFragment.newInstance(DiaryEntryViewModel.NEW_DIARY_ENTRY_ID);
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

    @Override
    public void onDetach() {
        super.onDetach();
        mFragmentListener = null;
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

    public static DiaryFragment newInstance() {
        return new DiaryFragment();
    }

    private OnFragmentListener mFragmentListener;
    protected DiaryEntriesViewModel mDiaryEntryListViewModel;
    protected DiaryEntryAdapter mAdapter;
    protected ActionBar mToolbar;
    private DiaryEntryClickCallback mDiaryEntryClickCallback = new DiaryEntryClickCallback() {
        @Override
        public void onClick(DiaryEntryViewModel model) {
            WorkoutDetailsFragment fragment = WorkoutDetailsFragment.newInstance(model.getEntry().get().getId());
            mFragmentListener.addFragmentOnTop(fragment);
        }
    };

    private int DELETE_ENTRIES_REQUEST_CODE = 76;
}
