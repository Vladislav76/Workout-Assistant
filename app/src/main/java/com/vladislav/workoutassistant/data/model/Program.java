package com.vladislav.workoutassistant.data.model;

import com.vladislav.workoutassistant.data.db.entity.SetEntity;

import java.util.List;

import androidx.room.Relation;

public class Program extends NamedObject {

    @Relation(parentColumn = "id", entityColumn = "program_id", entity = SetEntity.class)
    private List<Set> mSets;

    /* GETTERS */
    public List<Set> getSets() {
        return mSets;
    }

    /* SETTERS */
    public void setSets(List<Set> sets) {
        mSets = sets;
    }
}
