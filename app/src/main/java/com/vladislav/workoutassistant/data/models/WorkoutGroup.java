package com.vladislav.workoutassistant.data.models;

import com.vladislav.workoutassistant.data.db.entity.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutGroup {

    private NamedObject mData;
    private List<Workout> mWorkouts;

    public WorkoutGroup(NamedObject data) {
        mData = data;
        mWorkouts = new ArrayList<>();
    }

    /* GETTERS */
    public int getGroupId() {
        return mData.getId();
    }

    public String getName() {
        return mData.getName();
    }

    public List<Workout> getWorkouts() {
        return mWorkouts;
    }

    /* SETTERS */
    public void setWorkouts(List<Workout> workouts) {
        mWorkouts = workouts;
    }
}
