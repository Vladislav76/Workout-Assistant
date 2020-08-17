package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_list

import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout.ShortWorkout
import io.reactivex.Observable

interface GetWorkoutListUC {

    fun getAllWorkouts(): Observable<List<ShortWorkout>>
}