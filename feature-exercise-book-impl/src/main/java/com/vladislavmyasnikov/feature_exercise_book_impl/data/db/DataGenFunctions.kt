package com.vladislavmyasnikov.feature_exercise_book_impl.data.db

import com.vladislavmyasnikov.feature_exercise_book_impl.data.db.entities.FullExerciseInfo

fun generateExercisesInfo(amount: Int): List<FullExerciseInfo> {
    val exercisesInfo = mutableListOf<FullExerciseInfo>()
    for (i in 1..amount) {
        exercisesInfo.add(FullExerciseInfo(title = "Title $i", description = "Description $i"))
    }
    return exercisesInfo
}