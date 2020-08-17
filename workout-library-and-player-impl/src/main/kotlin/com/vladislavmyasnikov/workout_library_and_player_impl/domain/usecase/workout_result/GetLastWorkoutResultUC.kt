package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_result

import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_execution.WorkoutResult
import io.reactivex.Single

interface GetLastWorkoutResultUC {

    operator fun invoke(): Single<WorkoutResult>
}