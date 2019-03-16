package com.vladislav.workoutassistant.data.model;

import java.util.List;

import androidx.room.Relation;

public class Program extends NamedObject {

    @Relation(parentColumn = "id", entityColumn = "program_id", entity = com.vladislav.workoutassistant.data.db.entity.Set.class)
    private List<com.vladislav.workoutassistant.data.model.Set> mSets;

    /* GETTERS */
    public List<com.vladislav.workoutassistant.data.model.Set> getSets() {
        return mSets;
    }

    /* SETTERS */
    public void setSets(List<com.vladislav.workoutassistant.data.model.Set> sets) {
        mSets = sets;
    }
}
