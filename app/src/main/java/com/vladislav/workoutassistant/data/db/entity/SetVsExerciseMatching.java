package com.vladislav.workoutassistant.data.db.entity;

import com.vladislav.workoutassistant.data.models.RepeatableObject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "sets_vs_exercises", foreignKeys = {@ForeignKey(entity = Set.class,
                                                                   parentColumns = "id",
                                                                   childColumns = "set_id",
                                                                   onDelete = CASCADE),
                                                        @ForeignKey(entity = Exercise.class,
                                                                    parentColumns = "id",
                                                                    childColumns = "exercise_id",
                                                                    onDelete = CASCADE)})
public class SetVsExerciseMatching extends RepeatableObject {

    @ColumnInfo(name = "set_id", index = true)
    private int mSetId;

    @ColumnInfo(name = "exercise_id", index = true)
    private int mExerciseId;

    public SetVsExerciseMatching(int setId, int exerciseId, int reps) {
        mSetId = setId;
        mExerciseId = exerciseId;
        mReps = reps;
    }

    /* GETTERS */
    public int getSetId() {
        return mSetId;
    }

    public int getExerciseId() {
        return mExerciseId;
    }
}
