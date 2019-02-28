package com.vladislav.workoutassistant.data.db.entity;

import com.vladislav.workoutassistant.data.model.NamedObject;
import com.vladislav.workoutassistant.data.model.RepeatableObject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercises")
public class ExerciseEntity extends NamedObject {

    public ExerciseEntity(String name, String description, int muscleGroup) {
        setName(name);
        this.mDescription = description;
        this.mMuscleGroup = muscleGroup;
    }

    @ColumnInfo(name = "description")
    private String mDescription;

    @ColumnInfo(name = "muscle_group")
    private int mMuscleGroup;

    /* GETTERS */
    public String getDescription() {
        return mDescription;
    }

    public int getMuscleGroup() {
        return mMuscleGroup;
    }
}
