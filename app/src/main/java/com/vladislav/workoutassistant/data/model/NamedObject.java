package com.vladislav.workoutassistant.data.model;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class NamedObject {

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

    public void setName(String name) {
        mName = name;
    }
}
