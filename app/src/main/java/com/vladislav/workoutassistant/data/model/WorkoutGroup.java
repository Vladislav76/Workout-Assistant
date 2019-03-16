package com.vladislav.workoutassistant.data.model;

import com.vladislav.workoutassistant.data.db.entity.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutGroup {

    private int mGroupId;
    private List<Workout> mWorkouts;

    public WorkoutGroup(int groupId) {
        mGroupId = groupId;
        mWorkouts = new ArrayList<>();
    }

    /* GETTERS */
    public int getGroupId() {
        return mGroupId;
    }

    public List<Workout> getWorkouts() {
        return mWorkouts;
    }

    /* SETTERS */
    public void setWorkouts(List<Workout> workouts) {
        mWorkouts = workouts;
    }
}
