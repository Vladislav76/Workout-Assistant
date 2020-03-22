package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.ShortWorkout
import io.reactivex.Observable

interface GetWorkoutPlansUC {

    operator fun invoke(): Observable<List<ShortWorkout>>
}