package com.vladislav.workoutassistant.utilities;

import android.util.Log;

import com.vladislav.workoutassistant.data.models.WorkoutExercise;
import com.vladislav.workoutassistant.data.models.WorkoutProgram;
import com.vladislav.workoutassistant.data.models.WorkoutSet;

public class Logging {

    public static void printWorkoutProgram(WorkoutProgram workoutProgram) {
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
