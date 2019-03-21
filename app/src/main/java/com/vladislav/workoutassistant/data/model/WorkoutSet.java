package com.vladislav.workoutassistant.data.model;

import com.vladislav.workoutassistant.data.db.entity.Set;
import com.vladislav.workoutassistant.data.db.entity.SetVsExerciseMatching;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class WorkoutSet {

    @Embedded
    public Set setInfo;

    @Relation(parentColumn = "id", entityColumn = "set_id", entity = SetVsExerciseMatching.class)
    private List<WorkoutExercise> mWorkoutExercises;

    /* GETTERS */
    public List<WorkoutExercise> getWorkoutExercises() {
        return mWorkoutExercises;
    }

    /* SETTERS */
    public void setWorkoutExercises(List<WorkoutExercise> workoutExercises) {
        mWorkoutExercises = workoutExercises;
    }
}
