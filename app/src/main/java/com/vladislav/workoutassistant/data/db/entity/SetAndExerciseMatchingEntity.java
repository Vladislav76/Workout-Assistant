package com.vladislav.workoutassistant.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "exercise_matching", foreignKeys = {@ForeignKey(entity = SetEntity.class,
                                                                   parentColumns = "id",
                                                                   childColumns = "set_id",
                                                                   onDelete = CASCADE),
                                                        @ForeignKey(entity = ExerciseEntity.class,
                                                                    parentColumns = "id",
                                                                    childColumns = "exercise_id",
                                                                    onDelete = CASCADE)})
public class SetAndExerciseMatchingEntity {

    public SetAndExerciseMatchingEntity(int setId, int exerciseId, int reps) {
        this.mSetId = setId;
        this.mExerciseId = exerciseId;
        this.mReps = reps;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "set_id", index = true)
    private int mSetId;

    @ColumnInfo(name = "exercise_id", index = true)
    private int mExerciseId;

    @ColumnInfo(name = "reps")
    private int mReps;

    /* GETTERS */
    public int getId() {
        return mId;
    }

    public int getSetId() {
        return mSetId;
    }

    public int getExerciseId() {
        return mExerciseId;
    }

    public int getReps() {
        return mReps;
    }

    /* SETTERS */
    public void setId(int id) {
        mId = id;
    }
}
