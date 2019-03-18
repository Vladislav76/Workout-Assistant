package com.vladislav.workoutassistant.data.model;

import com.vladislav.workoutassistant.data.db.entity.Exercise;
import com.vladislav.workoutassistant.data.db.entity.SetVsExerciseMatching;

import androidx.room.Embedded;
import androidx.room.Ignore;

public class WorkoutExercise {

    @Embedded
    public SetVsExerciseMatching matchingInfo;

    @Ignore
    public Exercise exerciseInfo;
}
