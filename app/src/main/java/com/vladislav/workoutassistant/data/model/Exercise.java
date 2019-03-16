package com.vladislav.workoutassistant.data.model;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Relation;

public class Exercise extends RepeatableObject {

    @ColumnInfo(name = "exercise_id")
    private int mExerciseId;

    @Relation(parentColumn = "exercise_id", entityColumn = "id", entity = com.vladislav.workoutassistant.data.db.entity.Exercise.class)
    private List<com.vladislav.workoutassistant.data.db.entity.Exercise> mInfo;

    /* GETTERS */
    public int getExerciseId() {
        return mExerciseId;
    }

    public List<com.vladislav.workoutassistant.data.db.entity.Exercise> getInfo() {
        return mInfo;
    }

    public String getExerciseName() {
        if (mInfo != null && mInfo.size() > 0) {
            return mInfo.get(0).getName();
        }
        return "";
    }

    public String getExerciseDescription() {
        if (mInfo != null && mInfo.size() > 0) {
            return mInfo.get(0).getDescription();
        }
        return "";
    }

    /* SETTERS */
    public void setExerciseId(int exerciseId) {
        mExerciseId = exerciseId;
    }

    public void setInfo(List<com.vladislav.workoutassistant.data.db.entity.Exercise> info) {
        mInfo = info;
    }
}
