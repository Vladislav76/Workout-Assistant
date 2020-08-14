package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.completed_workout_list

import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.ShortCompletedWorkout
import io.reactivex.Observable

interface GetCompletedWorkoutListUC {

    fun getAllCompletedWorkouts(): Observable<List<ShortCompletedWorkout>>
}