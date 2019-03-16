package com.vladislav.workoutassistant.data.db.entity;

import com.vladislav.workoutassistant.data.model.RepeatableObject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "sets")
public class Set extends RepeatableObject {

    @ColumnInfo(name = "program_id")
    private int mProgramId;

    public Set(int reps, int programId) {
        setReps(reps);
        this.mProgramId = programId;
    }

    /* GETTERS */
    public int getProgramId() {
        return mProgramId;
    }
}
