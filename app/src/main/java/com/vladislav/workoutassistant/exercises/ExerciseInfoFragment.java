package com.vladislav.workoutassistant.exercises;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.db.entity.Exercise;
import com.vladislav.workoutassistant.databinding.FragmentExerciseInfoBinding;
import com.vladislav.workoutassistant.exercises.viewmodels.ExerciseViewModel;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class ExerciseInfoFragment extends Fragment {

    private static final String EXERCISE_ID_ARG = "exercise_id_arg";

    private FragmentExerciseInfoBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_exercise_info, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        ExerciseViewModel exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);
        exerciseViewModel.getExercise().observe(this, new Observer<Exercise>() {
            @Override
            public void onChanged(Exercise exercise) {
                mBinding.setExercise(exercise);
            }
        });
        exerciseViewModel.init(getArguments().getInt(EXERCISE_ID_ARG));
    }

    /* F A C T O R Y */
    public static ExerciseInfoFragment newInstance(int exerciseId) {
        Bundle args = new Bundle();
        args.putInt(EXERCISE_ID_ARG, exerciseId);

        ExerciseInfoFragment fragment = new ExerciseInfoFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
