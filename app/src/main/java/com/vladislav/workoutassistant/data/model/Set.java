package com.vladislav.workoutassistant.data.model;

import com.vladislav.workoutassistant.data.db.entity.SetVsExerciseMatching;

import java.util.List;

import androidx.room.Relation;

public class Set extends RepeatableObject {

    @Relation(parentColumn = "id", entityColumn = "set_id", entity = SetVsExerciseMatching.class)
    private List<Exercise> mExercises;

    /* GETTERS */
    public List<Exercise> getExercises() {
        return mExercises;
    }

    /* SETTERS */
    public void setExercises(List<Exercise> exercises) {
        mExercises = exercises;
    }
}
