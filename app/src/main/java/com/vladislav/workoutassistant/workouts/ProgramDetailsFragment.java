package com.vladislav.workoutassistant.workouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.model.Program;
import com.vladislav.workoutassistant.core.GeneralFragment;
import com.vladislav.workoutassistant.workouts.adapters.SetAndExerciseAdapter;
import com.vladislav.workoutassistant.core.callbacks.ItemClickCallback;
import com.vladislav.workoutassistant.workouts.viewmodels.ProgramViewModel;
import com.vladislav.workoutassistant.workouts.viewmodels.ProgramViewModelFactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class ProgramDetailsFragment extends GeneralFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        updateToolbar(getArguments().getString(TOOLBAR_TITLE_ARG));

        View view = inflater.inflate(R.layout.fragment_program_details, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        if (mAdapter == null) {
            mAdapter = new SetAndExerciseAdapter(mCallback);
        }
        recyclerView.setAdapter(mAdapter);

        ProgramViewModelFactory factory = new ProgramViewModelFactory(getActivity().getApplication(), getArguments().getInt(PROGRAM_ID_ARG));
        ProgramViewModel programViewModel = ViewModelProviders.of(this, factory).get(ProgramViewModel.class);
        programViewModel.getProgram().observe(this, new Observer<Program>() {
            @Override
            public void onChanged(Program program) {
                if (program != null) {
                    mAdapter.setContent(program.getSets());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        return view;
    }

    public static ProgramDetailsFragment newInstance(int programId, String title) {
        Bundle args = new Bundle();
        args.putInt(PROGRAM_ID_ARG, programId);
        args.putString(TOOLBAR_TITLE_ARG, title);

        ProgramDetailsFragment fragment = new ProgramDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private SetAndExerciseAdapter mAdapter;
    private ItemClickCallback mCallback = new ItemClickCallback() {
        @Override
        public void onClick(int id, String name) {
            //TODO: show exercise info
        }
    };

    private static final String PROGRAM_ID_ARG = "program_id_arg";
    private static final String TOOLBAR_TITLE_ARG = "toolbar_title_arg";
}
