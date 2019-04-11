package com.vladislav.workoutassistant.ui.main.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.ui.main.fragments.CountingFragment;
import com.vladislav.workoutassistant.ui.exercises.ExerciseInfoFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ExerciseDetailsFragment extends DialogFragment implements View.OnClickListener {

    public static final String EXERCISE_ID_DATA = "exercise_id_data";
    public static final String EXERCISE_COUNT_DATA = "exercise_count_data";

    private static final String EXERCISE_ID_ARG = "exercise_id_arg";
    private static final String EXERCISE_COUNT_ARG = "exercise_count_arg";

    private static final String EXERCISE_INFO_FRAGMENT_TAG = "exercise_info_fragment";
    private static final String COUNTING_FRAGMENT_TAG = "counting_fragment";

    private CountingFragment mChildCountingFragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exercise_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        FragmentManager fm = getChildFragmentManager();

        Button okButton = view.findViewById(R.id.ok_button);
        okButton.setOnClickListener(this);

        if (fm.findFragmentByTag(EXERCISE_INFO_FRAGMENT_TAG) == null) {
            ExerciseInfoFragment exerciseInfoFragment = ExerciseInfoFragment.Companion.newInstance(getArguments().getInt(EXERCISE_ID_ARG));
            fm.beginTransaction()
                    .replace(R.id.exercise_content, exerciseInfoFragment)
                    .commit();
        }

        int exerciseReps = getArguments().getInt(EXERCISE_COUNT_ARG, -1);
        if (exerciseReps != -1) {
            Button cancelButton = view.findViewById(R.id.cancel_button);
            cancelButton.setVisibility(View.VISIBLE);
            cancelButton.setOnClickListener(this);

            Button removeButton = view.findViewById(R.id.remove_button);
            removeButton.setVisibility(View.VISIBLE);
            removeButton.setOnClickListener(this);

            if ((mChildCountingFragment = (CountingFragment) fm.findFragmentByTag(COUNTING_FRAGMENT_TAG)) == null) {
                mChildCountingFragment = CountingFragment.Companion.newInstance(exerciseReps, CountingFragment.MeasureUnit.KG);
                fm.beginTransaction()
                        .replace(R.id.another_content, mChildCountingFragment, COUNTING_FRAGMENT_TAG)
                        .commit();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok_button:
                dismiss();
                sendResult(Activity.RESULT_OK);
                break;
            case R.id.cancel_button:
                dismiss();
                break;
            case R.id.remove_button:
                dismiss();
                sendResult(Activity.RESULT_CANCELED);
                break;
        }
    }

    /* F A StringConverter T O R Y */
    public static ExerciseDetailsFragment newInstance(int exerciseId) {
        Bundle args = new Bundle();
        args.putInt(EXERCISE_ID_ARG, exerciseId);

        ExerciseDetailsFragment fragment = new ExerciseDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public static ExerciseDetailsFragment newInstance(int exerciseId, int exerciseCount) {
        ExerciseDetailsFragment fragment = ExerciseDetailsFragment.newInstance(exerciseId);
        fragment.getArguments().putInt(EXERCISE_COUNT_ARG, exerciseCount);

        return fragment;
    }

    /* P R I V A T E */
    private void sendResult(int resultCode) {
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            Intent intent = new Intent();
            intent.putExtra(EXERCISE_ID_DATA, getArguments().getInt(EXERCISE_ID_ARG));

            if (resultCode == Activity.RESULT_OK && getArguments().get(EXERCISE_COUNT_ARG) != null) {
                intent.putExtra(EXERCISE_COUNT_DATA, mChildCountingFragment.getCurrentValue());
            }
            targetFragment.onActivityResult(getTargetRequestCode(), resultCode, intent);
        }
    }
}
