package com.vladislavmyasnikov.feature_workout_library_impl.data.db

import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entity.WorkoutEntity
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entity.WorkoutExerciseEntity
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entity.WorkoutSetEntity

private val imagesNames = listOf("work_dummy_1", "work_dummy_2", "work_dummy_3")

fun generateWorkoutExerciseList(exerciseAmountRange: IntRange, approachAmount: Int, exerciseIDRange: LongRange, repsAmountRange: IntRange, weightsRange: IntRange): List<WorkoutExerciseEntity> {
    val list = mutableListOf<WorkoutExerciseEntity>()
    val offsetRange = IntRange(1, 10)

    for (i in 1..exerciseAmountRange.random()) {
        val reps = mutableListOf<Int>()
        val weights = mutableListOf<Float>()

        for (k in 1..approachAmount) {
            reps.add(repsAmountRange.random())
            weights.add(weightsRange.random() + 0.1F * offsetRange.random())
        }

        list.add(WorkoutExerciseEntity(exerciseId = exerciseIDRange.random(), reps = reps, weights = weights))
    }
    return list
}

fun generateWorkoutSetList(workoutExerciseIDs: List<List<Long>>): List<WorkoutSetEntity> {
    val list = mutableListOf<WorkoutSetEntity>()

    for (ids in workoutExerciseIDs) {
        list.add(WorkoutSetEntity(workoutExerciseIDs = ids))
    }
    return list
}

fun generateWorkoutList(workoutSetIDs: List<List<Long>>): List<WorkoutEntity> {
    val list = mutableListOf<WorkoutEntity>()

    for ((index, ids) in workoutSetIDs.withIndex()) {
        list.add(WorkoutEntity(title = "Title ${index + 1}", avatarID = imagesNames.random(), description = "Description ${index + 1}", workoutSetIDs = ids))
    }
    return list
}