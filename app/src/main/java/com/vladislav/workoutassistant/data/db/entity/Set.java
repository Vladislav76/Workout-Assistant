package com.vladislav.workoutassistant.data.db.entity;

import com.vladislav.workoutassistant.data.models.RepeatableObject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "sets")
public class Set extends RepeatableObject {

    @ColumnInfo(name = "workout_id")
    private int mProgramId;

    public Set(int reps, int programId) {
        mReps = reps;
        mProgramId = programId;
    }

    /* GETTERS */
    public int getProgramId() {
        return mProgramId;
    }
}
