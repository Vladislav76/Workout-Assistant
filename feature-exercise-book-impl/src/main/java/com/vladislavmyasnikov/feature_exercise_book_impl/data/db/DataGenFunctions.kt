package com.vladislavmyasnikov.feature_exercise_book_impl.data.db

import com.vladislavmyasnikov.feature_exercise_book_impl.data.db.entities.FullExerciseInfo

fun generateExercisesInfo(amount: Int, muscleGroupsAmount: Int): List<FullExerciseInfo> {
    val exercisesInfo = mutableListOf<FullExerciseInfo>()
    val range = 0 until muscleGroupsAmount
    for (i in 1..amount) {
        val ids = mutableListOf<Int>()
        for (k in 0..range.random()) {
            ids.add(range.random())
        }
        exercisesInfo.add(FullExerciseInfo(title = "Title $i", muscleGroupsIDs = ids, description = "Description $i"))
    }
    return exercisesInfo
}