package com.vladislav.workoutassistant.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "programs")
public class ProgramEntity {

    public ProgramEntity(String name, int categoryId) {
        this.mName = name;
        this.mCategoryId = categoryId;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "category_id")
    private int mCategoryId;

    /* GETTERS */
    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getCategoryId() {
        return mCategoryId;
    }

    /* SETTERS */
    public void setId(int id) {
        mId = id;
    }
}
