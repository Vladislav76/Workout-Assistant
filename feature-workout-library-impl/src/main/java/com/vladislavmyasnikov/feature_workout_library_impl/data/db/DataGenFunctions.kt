package com.vladislavmyasnikov.feature_workout_library_impl.data.db

import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.SetInfo
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutInfo

fun generateSetsInfo(setsPerWorkout: IntRange, exercisesPerSet: IntRange, repsPerSet: IntRange, repsPerExercise: IntRange, maxExerciseID: Long): List<SetInfo> {
    val result = mutableListOf<SetInfo>()
    val exercisesIDsRange = 1L..maxExerciseID

    for (i in 1..setsPerWorkout.random()) {
        val exercisesIDs = mutableListOf<Long>()
        val exercisesReps = mutableListOf<List<Int>>()
        val setReps = repsPerSet.random()

        for (k in 1..exercisesPerSet.random()) {
            val reps = mutableListOf<Int>()
            for (n in 1..setReps) {
                reps.add(repsPerExercise.random())
            }
            exercisesReps.add(reps)
            exercisesIDs.add(exercisesIDsRange.random())
        }

        val setInfo = SetInfo(exercisesIDs = exercisesIDs, exercisesReps = exercisesReps)
        result.add(setInfo)
    }
    return result
}

fun generateWorkoutsInfo(setsIDs: List<List<Long>>): List<WorkoutInfo> {
    val result = mutableListOf<WorkoutInfo>()
    for ((index, ids) in setsIDs.withIndex()) {
        val workout = WorkoutInfo(title = "Title ${index + 1}", description = "Description ${index + 1}", setsIDs = ids)
        result.add(workout)
    }
    return result
}