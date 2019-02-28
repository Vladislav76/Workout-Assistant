package com.vladislav.workoutassistant.data.db.entity;

import com.vladislav.workoutassistant.data.model.RepeatableObject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "sets")//, foreignKeys = @ForeignKey(entity = ProgramEntity.class,
                             //                         parentColumns = "id",
                               //                       childColumns = "program_id"))
public class SetEntity extends RepeatableObject {

    public SetEntity(int reps, int programId) {
        setReps(reps);
        this.mProgramId = programId;
    }

    @ColumnInfo(name = "program_id")
    private int mProgramId;

    /* GETTERS */
    public int getProgramId() {
        return mProgramId;
    }
}
