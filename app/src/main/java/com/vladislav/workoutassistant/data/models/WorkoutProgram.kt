package com.vladislav.workoutassistant.data.models

import com.vladislav.workoutassistant.data.db.entity.Set
import com.vladislav.workoutassistant.data.db.entity.Workout

import androidx.room.Embedded
import androidx.room.Relation

class WorkoutProgram {

    @Embedded
    var workoutInfo: Workout? = null

    @Relation(parentColumn = "id", entityColumn = "workout_id", entity = Set::class)
    var workoutSets: List<WorkoutSet>? = null
}
