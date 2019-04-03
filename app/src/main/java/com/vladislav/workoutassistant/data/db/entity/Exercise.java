package com.vladislav.workoutassistant.data.db.entity;

import com.vladislav.workoutassistant.data.models.Identifiable;
import com.vladislav.workoutassistant.data.models.NamedObject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "exercises")
public class Exercise extends NamedObject implements Identifiable {

    @ColumnInfo(name = "description")
    private String mDescription;

    @ColumnInfo(name = "muscle_group")
    private int mMuscleGroupId;

    public Exercise(String name, String description, int muscleGroupId) {
        mName = name;
        mDescription = description;
        mMuscleGroupId = muscleGroupId;
    }

    /* GETTERS */
    public String getDescription() {
        return mDescription;
    }

    public int getMuscleGroupId() {
        return mMuscleGroupId;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Exercise exercise = (Exercise) other;
        return mId == exercise.getId() &&
                mMuscleGroupId == exercise.mMuscleGroupId &&
                mName.equals(exercise.getName()) &&
                mDescription.equals(exercise.getDescription());
    }
}
