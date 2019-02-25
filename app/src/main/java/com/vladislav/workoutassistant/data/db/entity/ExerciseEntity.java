package com.vladislav.workoutassistant.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercises")
public class ExerciseEntity {

    public ExerciseEntity(String name, String description, int muscleGroup) {
        this.mName = name;
        this.mDescription = description;
        this.mMuscleGroup = muscleGroup;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "description")
    private String mDescription;

    @ColumnInfo(name = "muscle_group")
    private int mMuscleGroup;

    /* GETTERS */
    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getMuscleGroup() {
        return mMuscleGroup;
    }

    /* SETTERS */
    public void setId(int id) {
        mId = id;
    }
}
