package com.vladislav.workoutassistant.data.model;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class RepeatableObject {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    protected int mId;

    @ColumnInfo(name = "reps")
    protected int mReps;

    /* GETTERS */
    public int getId() {
        return mId;
    }

    public int getReps() {
        return mReps;
    }

    /* SETTERS */
    public void setId(int id) {
        mId = id;
    }

    public void setReps(int reps) {
        mReps = reps;
    }
}
