package com.vladislav.workoutassistant.data.db.entity;

import com.vladislav.workoutassistant.data.model.NamedObject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "workouts")
public class Workout extends NamedObject {

    @ColumnInfo(name = "category_id")
    private int mCategoryId;

    @ColumnInfo(name = "intensity_level_id")
    private int mIntensityLevelId;

    public Workout(String name, int categoryId, int intensityLevelId) {
        mName = name;
        mCategoryId = categoryId;
        mIntensityLevelId = intensityLevelId;
    }

    /* GETTERS */
    public int getCategoryId() {
        return mCategoryId;
    }

    public int getIntensityLevelId() {
        return mIntensityLevelId;
    }
}
