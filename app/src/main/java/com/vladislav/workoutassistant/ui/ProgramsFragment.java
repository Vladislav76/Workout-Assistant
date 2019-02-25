package com.vladislav.workoutassistant.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.data.db.entity.ProgramEntity;
import com.vladislav.workoutassistant.ui.adapters.ProgramAdapter;
import com.vladislav.workoutassistant.ui.callbacks.ItemClickCallback;
import com.vladislav.workoutassistant.viewmodels.ProgramsViewModel;
import com.vladislav.workoutassistant.viewmodels.ProgramsViewModelFactory;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProgramsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView view = new RecyclerView(getActivity());

        mAdapter = new ProgramAdapter(mCallback);
        view.setLayoutManager(new LinearLayoutManager(getActivity()));
        view.setAdapter(mAdapter);

        int categoryId = getArguments().getInt(CATEGORY_ID_ARG);
        ProgramsViewModelFactory factory = new ProgramsViewModelFactory(getActivity().getApplication(), categoryId);
        mProgramsViewModel = ViewModelProviders.of(this, factory).get(ProgramsViewModel.class);
        mProgramsViewModel.getPrograms().observe(this, new Observer<List<ProgramEntity>>() {
            @Override
            public void onChanged(List<ProgramEntity> programs) {
                if (programs != null) {
                    mAdapter.setList(programs);
                    mAdapter.notifyItemChanged(0, programs.size());
                }
            }
        });

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return view;
    }

    public static ProgramsFragment newInstance(int categoryId) {
        Bundle args = new Bundle();
        args.putInt(CATEGORY_ID_ARG, categoryId);

        ProgramsFragment fragment = new ProgramsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private ProgramAdapter mAdapter;
    private ProgramsViewModel mProgramsViewModel;
    private ItemClickCallback mCallback = new ItemClickCallback() {
        @Override
        public void onClick(int id) {
            //TODO: start new ProgramDetailsActivity
        }
    };

    private static final String CATEGORY_ID_ARG = "category_id_arg";
}
