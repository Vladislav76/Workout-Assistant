package com.vladislav.workoutassistant.exercises;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.core.GeneralFragment;
import com.vladislav.workoutassistant.core.callbacks.ItemClickCallback;
import com.vladislav.workoutassistant.core.components.CustomItemDecoration;
import com.vladislav.workoutassistant.data.db.entity.Exercise;
import com.vladislav.workoutassistant.exercises.adapters.ExerciseAdapter;
import com.vladislav.workoutassistant.exercises.viewmodels.ExerciseListViewModel;
import com.vladislav.workoutassistant.workouts.adapters.CategoryAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class ExercisesFragment extends GeneralFragment {

    private static final String CURRENT_MUSCLE_GROUP_ID = "current_muscle_group_id";
    private static final String SELECTION_MODE_ARG = "selection_mode_arg";
    private static final int SINGLE_SELECTION_MODE = 1;
    private static final int MULTIPLE_SELECTION_MODE = 2;

    private ExerciseListViewModel mExerciseListViewModel;
    private int mCurrentMuscleGroupId;
    private int mCurrentMode;

    private ItemClickCallback mExerciseClickCallback;
    private ItemClickCallback mMuscleGroupClickCallback = new ItemClickCallback() {
        @Override
        public void onClick(int id, String name) {
            if (id != mCurrentMuscleGroupId) {
                mCurrentMuscleGroupId = id;
                mExerciseListViewModel.init(id);
            }
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_horizontal_and_vertical_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        updateToolbar(R.string.exercises_tab);

        if (savedInstanceState != null) {
            mCurrentMuscleGroupId = savedInstanceState.getInt(CURRENT_MUSCLE_GROUP_ID);
        }

        Bundle args = getArguments();
        mCurrentMode = args.getInt(SELECTION_MODE_ARG);
        mExerciseClickCallback = getExerciseClickCallback(mCurrentMode);

        mExerciseListViewModel = ViewModelProviders.of(this).get(ExerciseListViewModel.class);

        RecyclerView muscleGroupsRecyclerView = view.findViewById(R.id.horizontal_recycler_view);
        CategoryAdapter muscleGroupAdapter = new CategoryAdapter(mExerciseListViewModel.getMuscleGroups(), mMuscleGroupClickCallback);
        muscleGroupsRecyclerView.setAdapter(muscleGroupAdapter);
        muscleGroupsRecyclerView.addItemDecoration(new CustomItemDecoration(10));

        RecyclerView exercisesRecyclerView = view.findViewById(R.id.vertical_recycler_view);
        final ExerciseAdapter exerciseAdapter = new ExerciseAdapter(mExerciseClickCallback, mCurrentMode == MULTIPLE_SELECTION_MODE);
        exercisesRecyclerView.setAdapter(exerciseAdapter);
        mExerciseListViewModel.getExercises().observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                if (exercises != null) {
                    exerciseAdapter.updateList(exercises);
                }
            }
        });
        mExerciseListViewModel.init(mCurrentMuscleGroupId);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putInt(CURRENT_MUSCLE_GROUP_ID, mCurrentMuscleGroupId);
    }

    public static ExercisesFragment newSingleSelectionModeInstance() {
        Bundle args = new Bundle();
        args.putInt(SELECTION_MODE_ARG, SINGLE_SELECTION_MODE);

        ExercisesFragment fragment = new ExercisesFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public static ExercisesFragment newMultipleSelectionModeInstance() {
        Bundle args = new Bundle();
        args.putInt(SELECTION_MODE_ARG, MULTIPLE_SELECTION_MODE);

        ExercisesFragment fragment = new ExercisesFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private static ItemClickCallback getExerciseClickCallback(int mode) {
        if (mode == SINGLE_SELECTION_MODE) {
            return new ItemClickCallback() {
                @Override
                public void onClick(int id, String name) {
                    Log.d("SINGLE_SELECTION", name);
                    //TODO: create a fragment with details of an exercise
                }
            };
        } else {
            return new ItemClickCallback() {
                @Override
                public void onClick(int id, String name) {
                    Log.d("MULTIPLE_SELECTION", name);
                    //TODO: create a fragment with details of an exercise and repetitions adjusting
                }
            };
        }
    }
}
