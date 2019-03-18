package com.vladislav.workoutassistant.data.db.entity;

import com.vladislav.workoutassistant.data.model.NamedObject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "exercises")
public class Exercise extends NamedObject {

    @ColumnInfo(name = "description")
    private String mDescription;

    @ColumnInfo(name = "muscle_group")
    private int mMuscleGroup;

    public Exercise(String name, String description, int muscleGroup) {
        mName = name;
        mDescription = description;
        mMuscleGroup = muscleGroup;
    }

    /* GETTERS */
    public String getDescription() {
        return mDescription;
    }

    public int getMuscleGroup() {
        return mMuscleGroup;
    }
}
