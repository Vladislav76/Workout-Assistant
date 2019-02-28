package com.vladislav.workoutassistant.data.db.entity;

import com.vladislav.workoutassistant.data.model.NamedObject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class ProgramCategoryEntity extends NamedObject {

    public ProgramCategoryEntity(String name) {
        setName(name);
    }
}
