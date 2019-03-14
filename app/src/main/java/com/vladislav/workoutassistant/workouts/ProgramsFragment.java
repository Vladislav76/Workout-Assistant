package com.vladislav.workoutassistant.workouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.data.db.entity.ProgramEntity;
import com.vladislav.workoutassistant.core.GeneralFragment;
import com.vladislav.workoutassistant.workouts.adapters.ProgramAdapter;
import com.vladislav.workoutassistant.core.callbacks.ItemClickCallback;
import com.vladislav.workoutassistant.workouts.viewmodels.ProgramsViewModel;
import com.vladislav.workoutassistant.workouts.viewmodels.ProgramsViewModelFactory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProgramsFragment extends GeneralFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        updateToolbar(getArguments().getString(TOOLBAR_TITLE_ARG));

        final RecyclerView recyclerView = new RecyclerView(getActivity());

        if (mAdapter == null) {
            mAdapter = new ProgramAdapter(mCallback);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);

        ProgramsViewModelFactory factory = new ProgramsViewModelFactory(getActivity().getApplication(), getArguments().getInt(CATEGORY_ID_ARG));
        ProgramsViewModel programsViewModel = ViewModelProviders.of(this, factory).get(ProgramsViewModel.class);
        programsViewModel.getPrograms().observe(this, new Observer<List<ProgramEntity>>() {
            @Override
            public void onChanged(List<ProgramEntity> programs) {
                if (programs != null) {
                    mAdapter.setList(programs);
                    mAdapter.notifyItemChanged(0, programs.size());
                }
            }
        });

        return recyclerView;
    }

    public static ProgramsFragment newInstance(int categoryId, String title) {
        Bundle args = new Bundle();
        args.putInt(CATEGORY_ID_ARG, categoryId);
        args.putString(TOOLBAR_TITLE_ARG, title);

        ProgramsFragment fragment = new ProgramsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private ProgramAdapter mAdapter;
    private ItemClickCallback mCallback = new ItemClickCallback() {
        @Override
        public void onClick(int id, String title) {
            ProgramDetailsFragment fragment = ProgramDetailsFragment.newInstance(id, title);
            mFragmentListener.addFragmentOnTop(fragment);
        }
    };

    private static final String CATEGORY_ID_ARG = "category_id_arg";
    private static final String TOOLBAR_TITLE_ARG = "toolbar_title_arg";
}
