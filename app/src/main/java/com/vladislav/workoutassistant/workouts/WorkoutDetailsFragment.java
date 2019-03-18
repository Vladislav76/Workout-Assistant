package com.vladislav.workoutassistant.workouts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.core.GeneralFragment;
import com.vladislav.workoutassistant.data.model.WorkoutExercise;
import com.vladislav.workoutassistant.data.model.WorkoutProgram;
import com.vladislav.workoutassistant.data.model.WorkoutSet;
import com.vladislav.workoutassistant.workouts.viewmodels.WorkoutViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutDetailsFragment extends GeneralFragment {

    private static final String WORKOUT_ID_ARG = "workout_id_arg";
    private static final String TITLE_ARG = "title_arg";

    private RecyclerView mProgramRecyclerView;
    private WorkoutViewModel mWorkoutViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Bundle args = getArguments();
        updateToolbar(args.getString(TITLE_ARG));

        mProgramRecyclerView = view.findViewById(R.id.recycler_view);
        mWorkoutViewModel = ViewModelProviders.of(this).get(WorkoutViewModel.class);
        mWorkoutViewModel.getWorkoutProgram().observe(this, new Observer<WorkoutProgram>() {
            @Override
            public void onChanged(WorkoutProgram workoutProgram) {
                if (workoutProgram != null) {
                    printWorkoutProgram(workoutProgram);
                }
            }
        });
        mWorkoutViewModel.init(args.getInt(WORKOUT_ID_ARG));
    }

    public static WorkoutDetailsFragment newInstance(int id, String title) {
        Bundle args = new Bundle();
        args.putInt(WORKOUT_ID_ARG, id);
        args.putString(TITLE_ARG, title);

        WorkoutDetailsFragment fragment = new WorkoutDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private void printWorkoutProgram(WorkoutProgram workoutProgram) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n* * * * * W O R K O U T * * * * *\n");
        sb.append("category ID #");
        sb.append(workoutProgram.workoutInfo.getCategoryId());
        sb.append("\n");
        for (WorkoutSet workoutSet : workoutProgram.getWorkoutSets()) {
            sb.append("\n* * * * * S E T * * * * *\n");
            sb.append("\t* set ID #");
            sb.append(workoutSet.setInfo.getId());
            sb.append("\n\t* reps #");
            sb.append(workoutSet.setInfo.getReps());
            sb.append("\n");
            for (WorkoutExercise workoutExercise : workoutSet.getWorkoutExercises()) {
                sb.append("\n\t\t* exercise ID #");
                sb.append(workoutExercise.matchingInfo.getExerciseId());
                sb.append("\n\t\t* ");
                sb.append(workoutExercise.exerciseInfo.getName());
                sb.append("; ");
                sb.append(workoutExercise.exerciseInfo.getDescription());
                sb.append("\n\t\t* reps #");
                sb.append(workoutExercise.matchingInfo.getReps());
                sb.append("\n");
            }
        }
        Log.d("Workout Program Info", sb.toString());
    }
}
