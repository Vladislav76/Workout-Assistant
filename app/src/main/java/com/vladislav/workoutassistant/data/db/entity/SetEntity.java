package com.vladislav.workoutassistant.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "sets")//, foreignKeys = @ForeignKey(entity = ProgramEntity.class,
                             //                         parentColumns = "id",
                               //                       childColumns = "program_id"))
public class SetEntity {

    public SetEntity(String name, int reps, int programId) {
        this.mName = name;
        this.mReps = reps;
        this.mProgramId = programId;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "reps")
    private int mReps;

    @ColumnInfo(name = "program_id")
    private int mProgramId;

    /* GETTERS */
    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getReps() {
        return mReps;
    }

    public int getProgramId() {
        return mProgramId;
    }

    /* SETTERS */
    public void setId(int id) {
        mId = id;
    }
}
