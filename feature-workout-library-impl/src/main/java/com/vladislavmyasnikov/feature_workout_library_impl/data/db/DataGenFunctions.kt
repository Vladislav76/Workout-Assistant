package com.vladislavmyasnikov.feature_workout_library_impl.data.db

import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutEntity
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutExerciseEntity
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutSetEntity

private val imagesNames = listOf("work_dummy_1", "work_dummy_2", "work_dummy_3")


//fun generateWorkoutSetList(setsPerWorkout: IntRange, exercisesPerSet: IntRange, repsPerSet: IntRange, repsPerExercise: IntRange, maxExerciseID: Long): List<SetInfo> {
//    val result = mutableListOf<SetInfo>()
//    val exercisesIDsRange = 1L..maxExerciseID
//
//    for (i in 1..setsPerWorkout.random()) {
//        val exercisesIDs = mutableListOf<Long>()
//        val exercisesReps = mutableListOf<List<Int>>()
//        val setReps = repsPerSet.random()
//
//        for (k in 1..exercisesPerSet.random()) {
//            val reps = mutableListOf<Int>()
//            for (n in 1..setReps) {
//                reps.add(repsPerExercise.random())
//            }
//            exercisesReps.add(reps)
//            exercisesIDs.add(exercisesIDsRange.random())
//        }
//
//        val setInfo = SetInfo(exercisesIDs = exercisesIDs, exercisesReps = exercisesReps)
//        result.add(setInfo)
//    }
//    return result
//}

fun generateWorkoutExerciseList(exerciseAmountRange: IntRange, approachAmountRange: IntRange, exerciseIDRange: LongRange, repsAmountRange: IntRange, weightsRange: IntRange): List<WorkoutExerciseEntity> {
    val list = mutableListOf<WorkoutExerciseEntity>()
    val offsetRange = IntRange(1, 10)

    for (i in 1..exerciseAmountRange.random()) {
        val reps = mutableListOf<Int>()
        val weights = mutableListOf<Float>()

        for (k in 1..approachAmountRange.random()) {
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
//        val workoutExerciseList = generateWorkoutExerciseList(exerciseAmountRange.random(), approachAmountRange.random(), exerciseIDRange, repsAmountRange, weightsRange)
//        list.add(WorkoutSet(workoutExerciseIDs = workoutExerciseList.map { exercise -> exercise.id }))
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