package com.vladislavmyasnikov.workout_diary_impl.domain.usecase.completed_workout_details

import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.CompletedWorkout
import io.reactivex.Single

interface GetCompletedWorkoutUC {

    fun getCompletedWorkoutById(id: Long): Single<CompletedWorkout>
}