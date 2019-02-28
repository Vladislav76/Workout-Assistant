package com.vladislav.workoutassistant.data.db.entity;

import com.vladislav.workoutassistant.data.model.Exercise;
import com.vladislav.workoutassistant.data.model.RepeatableObject;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
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
public class SetAndExerciseMatchingEntity extends RepeatableObject {

    public SetAndExerciseMatchingEntity(int setId, int exerciseId, int reps) {
        this.mSetId = setId;
        this.mExerciseId = exerciseId;
        setReps(reps);
    }

    @ColumnInfo(name = "set_id", index = true)
    private int mSetId;

    @ColumnInfo(name = "exercise_id", index = true)
    private int mExerciseId;

    /* GETTERS */
    public int getSetId() {
        return mSetId;
    }

    public int getExerciseId() {
        return mExerciseId;
    }
}
