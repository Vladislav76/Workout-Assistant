package com.vladislavmyasnikov.workout_diary_impl.domain.usecase.completed_workout_list

import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.ShortCompletedWorkout
import io.reactivex.Observable

interface GetCompletedWorkoutListUC {

    fun getAllCompletedWorkouts(): Observable<List<ShortCompletedWorkout>>
}