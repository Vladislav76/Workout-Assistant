package com.vladislav.workoutassistant.data.models

import com.vladislav.workoutassistant.data.db.entity.Exercise
import com.vladislav.workoutassistant.data.db.entity.SetVsExerciseMatching

import androidx.room.Embedded
import androidx.room.Ignore

class WorkoutExercise {

    @Embedded
    var matchingInfo: SetVsExerciseMatching? = null

    @Ignore
    var exerciseInfo: Exercise? = null
}
