package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_list

import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.workout.ShortWorkout
import io.reactivex.Observable

interface GetWorkoutListUC {

    fun getAllWorkouts(): Observable<List<ShortWorkout>>
}