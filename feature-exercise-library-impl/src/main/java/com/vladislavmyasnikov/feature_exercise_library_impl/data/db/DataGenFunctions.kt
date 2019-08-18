package com.vladislavmyasnikov.feature_exercise_library_impl.data.db

import com.vladislavmyasnikov.feature_exercise_library_impl.data.db.entities.FullExerciseInfo

private val imagesNames = listOf("ex_dummy_1", "ex_dummy_2", "ex_dummy_3", "ex_dummy_4", "ex_dummy_5")

fun generateExercisesInfo(amount: Int, muscleGroupsAmount: Int): List<FullExerciseInfo> {
    val exercisesInfo = mutableListOf<FullExerciseInfo>()
    val muscleGroupsIDsRange = 0 until muscleGroupsAmount
    val imagesIDsRange = 0 until imagesNames.size

    for (i in 1..amount) {
        val muscleGroupsIDs = mutableListOf<Int>()
        for (k in 0..muscleGroupsIDsRange.random()) {
            muscleGroupsIDs.add(muscleGroupsIDsRange.random())
        }

        val imagesIDs = mutableListOf<String>()
        for (k in 0..imagesIDsRange.random()) {
            imagesIDs.add(imagesNames[imagesIDsRange.random()])
        }

        exercisesInfo.add(FullExerciseInfo(title = "Title $i", muscleGroupsIDs = muscleGroupsIDs, imagesIDs = imagesIDs, description = "Description $i"))
    }
    return exercisesInfo
}