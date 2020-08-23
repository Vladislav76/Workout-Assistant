package com.vladislavmyasnikov.exercise_library_impl.domain.usecase.exercise_details

import com.vladislavmyasnikov.exercise_library_impl.domain.entity.Exercise
import io.reactivex.Single

interface GetExerciseUC {

    fun getExerciseById(id: Long): Single<Exercise>
}