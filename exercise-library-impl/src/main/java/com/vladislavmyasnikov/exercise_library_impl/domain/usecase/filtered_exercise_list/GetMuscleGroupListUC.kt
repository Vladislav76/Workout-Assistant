package com.vladislavmyasnikov.exercise_library_impl.domain.usecase.filtered_exercise_list

import io.reactivex.Single

interface GetMuscleGroupListUC {

    fun getAllMuscleGroups(): Single<List<Pair<String,Boolean>>>
}