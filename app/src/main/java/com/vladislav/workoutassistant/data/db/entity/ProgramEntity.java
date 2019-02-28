package com.vladislav.workoutassistant.data.db.entity;

import com.vladislav.workoutassistant.data.model.NamedObject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "programs")
public class ProgramEntity extends NamedObject {

    public ProgramEntity(String name, int categoryId) {
        setName(name);
        this.mCategoryId = categoryId;
    }

    @ColumnInfo(name = "category_id")
    private int mCategoryId;

    /* GETTERS */
    public int getCategoryId() {
        return mCategoryId;
    }
}
