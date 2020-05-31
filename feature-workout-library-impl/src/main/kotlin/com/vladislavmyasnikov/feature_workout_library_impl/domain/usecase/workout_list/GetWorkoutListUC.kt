package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_list

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.ShortWorkout
import io.reactivex.Observable

interface GetWorkoutListUC {

    operator fun invoke(): Observable<List<ShortWorkout>>
}