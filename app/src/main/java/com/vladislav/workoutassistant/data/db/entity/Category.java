package com.vladislav.workoutassistant.data.db.entity;

import com.vladislav.workoutassistant.data.model.NamedObject;

import androidx.room.Entity;

@Entity(tableName = "categories")
public class Category extends NamedObject {

    public Category(String name) {
        setName(name);
    }
}
