package com.vladislav.workoutassistant.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class ProgramCategoryEntity {

    public ProgramCategoryEntity(String name) {
        mName = name;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    /* GETTERS */
    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    /* SETTERS */
    public void setId(int id) {
        mId = id;
    }
}
