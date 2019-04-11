package com.vladislav.workoutassistant.data.models

import com.vladislav.workoutassistant.data.db.entity.Set
import com.vladislav.workoutassistant.data.db.entity.SetVsExerciseMatching

import androidx.room.Embedded
import androidx.room.Relation

class WorkoutSet {

    @Embedded
    var setInfo: Set? = null

    @Relation(parentColumn = "id", entityColumn = "set_id", entity = SetVsExerciseMatching::class)
    var workoutExercises: List<WorkoutExercise>? = null
}
