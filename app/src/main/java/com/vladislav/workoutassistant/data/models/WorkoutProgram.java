package com.vladislav.workoutassistant.data.models;

import com.vladislav.workoutassistant.data.db.entity.Set;
import com.vladislav.workoutassistant.data.db.entity.Workout;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class WorkoutProgram {

    @Embedded
    public Workout workoutInfo;

    @Relation(parentColumn = "id", entityColumn = "workout_id", entity = Set.class)
    private List<WorkoutSet> mWorkoutSets;

    /* GETTERS */
    public List<WorkoutSet> getWorkoutSets() {
        return mWorkoutSets;
    }

    /* SETTERS */
    public void setWorkoutSets(List<WorkoutSet> workoutSets) {
        mWorkoutSets = workoutSets;
    }
}
