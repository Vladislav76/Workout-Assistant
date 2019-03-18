package com.vladislav.workoutassistant.data.model;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class NamedObject {

    public NamedObject() {}

    public NamedObject(int id, String name) {
        mId = id;
        mName = name;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    protected int mId;

    @ColumnInfo(name = "name")
    protected String mName;

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
