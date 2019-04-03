package com.vladislav.workoutassistant.ui.exercises;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.ui.main.GeneralFragment;
import com.vladislav.workoutassistant.ui.main.interfaces.OnItemClickCallback;
import com.vladislav.workoutassistant.ui.main.components.CustomItemDecoration;
import com.vladislav.workoutassistant.ui.main.dialogs.ExerciseDetailsFragment;
import com.vladislav.workoutassistant.data.db.entity.Exercise;
import com.vladislav.workoutassistant.ui.exercises.adapters.ExerciseAdapter;
import com.vladislav.workoutassistant.ui.exercises.viewmodels.ExerciseListViewModel;
import com.vladislav.workoutassistant.ui.workouts.adapters.CategoryAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class ExercisesFragment extends GeneralFragment {

    private static final String LAST_SELECTED_MUSCLE_GROUP_ID = "last_selected_muscle_group_id";
    private static final String LAST_CLICKED_ITEM_POSITION = "last_clicked_item_position";
    private static final String MULTIPLE_SELECTION_MODE_ENABLED_ARG = "multiple_selection_mode_enabled_arg";

    private static final int SHOW_EXERCISE_INFO_REQUEST_CODE = 1;
    private static final int GET_COUNT_DATA_REQUEST_CODE = 2;

    private ExerciseListViewModel mExerciseListViewModel;
    private ExerciseAdapter mExerciseAdapter;
    private CategoryAdapter mMuscleGroupAdapter;
    private int mCurrentMuscleGroupId;

    private OnItemClickCallback mExerciseClickCallback = new OnItemClickCallback() {
        @Override
        public void onClick(int id, String name) {
            showDialog(id);
        }
    };
    private OnItemClickCallback mMuscleGroupClickCallback = new OnItemClickCallback() {
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
        mFragmentListener.updateToolbarTitle(R.string.exercises_tab);

        mExerciseListViewModel = ViewModelProviders.of(this).get(ExerciseListViewModel.class);

        RecyclerView muscleGroupsRecyclerView = view.findViewById(R.id.horizontal_recycler_view);
        mMuscleGroupAdapter = new CategoryAdapter(mExerciseListViewModel.getMuscleGroups(), mMuscleGroupClickCallback);
        muscleGroupsRecyclerView.setAdapter(mMuscleGroupAdapter);
        muscleGroupsRecyclerView.addItemDecoration(new CustomItemDecoration(10));

        RecyclerView exercisesRecyclerView = view.findViewById(R.id.vertical_recycler_view);
        mExerciseAdapter = new ExerciseAdapter(mExerciseClickCallback, mExerciseListViewModel.getSelectedExercises());
        exercisesRecyclerView.setAdapter(mExerciseAdapter);
        mExerciseListViewModel.getExercises().observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                if (exercises != null) {
                    mExerciseAdapter.updateList(exercises);
                }
            }
        });

        if (savedInstanceState != null) {
            mCurrentMuscleGroupId = savedInstanceState.getInt(LAST_SELECTED_MUSCLE_GROUP_ID);
            mMuscleGroupAdapter.setItemPosition(mCurrentMuscleGroupId);

            mExerciseAdapter.setLastClickedItemPosition(savedInstanceState.getInt(LAST_CLICKED_ITEM_POSITION));
        }
        mExerciseListViewModel.init(mCurrentMuscleGroupId);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putInt(LAST_SELECTED_MUSCLE_GROUP_ID, mCurrentMuscleGroupId);
        savedInstanceState.putInt(LAST_CLICKED_ITEM_POSITION, mExerciseAdapter.getLastClickedItemPosition());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SHOW_EXERCISE_INFO_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                int exerciseId = intent.getIntExtra(ExerciseDetailsFragment.EXERCISE_ID_DATA, -1);

                Log.d("FROM_DIALOG_EX_ID:", String.valueOf(exerciseId));
            }
        } else if (requestCode == GET_COUNT_DATA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                int exerciseId = intent.getIntExtra(ExerciseDetailsFragment.EXERCISE_ID_DATA, -1);
                int exerciseCount = intent.getIntExtra(ExerciseDetailsFragment.EXERCISE_COUNT_DATA, -1);

                mExerciseListViewModel.setSelected(exerciseId, exerciseCount);
                mExerciseAdapter.notifyItemChanged(mExerciseAdapter.getLastClickedItemPosition());

                Log.d("FROM_DIALOG_EX_ID:", String.valueOf(exerciseId));
                Log.d("FROM_DIALOG_EX_COUNT:", String.valueOf(exerciseCount));
            } else if (resultCode == Activity.RESULT_CANCELED) {
                int exerciseId = intent.getIntExtra(ExerciseDetailsFragment.EXERCISE_ID_DATA, -1);

                mExerciseListViewModel.removeSelected(exerciseId);
                mExerciseAdapter.notifyItemChanged(mExerciseAdapter.getLastClickedItemPosition());

                Log.d("FROM_DIALOG_EX_ID:", String.valueOf(exerciseId));
            }
        }
    }

    /* F A C T O R Y */
    public static ExercisesFragment newInstance(boolean multipleSelectionModeEnabled) {
        Bundle args = new Bundle();
        args.putBoolean(MULTIPLE_SELECTION_MODE_ENABLED_ARG, multipleSelectionModeEnabled);

        ExercisesFragment fragment = new ExercisesFragment();
        fragment.setArguments(args);

        return fragment;
    }

    /* P R I V A T E */
    private void showDialog(int exerciseId) {
        FragmentManager fm = getFragmentManager();
        DialogFragment dialog;

        if (getArguments().getBoolean(MULTIPLE_SELECTION_MODE_ENABLED_ARG)) {
            dialog = ExerciseDetailsFragment.newInstance(exerciseId, mExerciseListViewModel.getExerciseCount(exerciseId, 0));
            dialog.setTargetFragment(this, GET_COUNT_DATA_REQUEST_CODE);
        } else {
            dialog = ExerciseDetailsFragment.newInstance(exerciseId);
            dialog.setTargetFragment(this, SHOW_EXERCISE_INFO_REQUEST_CODE);
        }
        dialog.show(fm, null);
    }
}
