package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_result.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_execution.WorkoutResult
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_result.GetLastWorkoutResultUC
import io.reactivex.Completable
import javax.inject.Inject

class WorkoutResultVM @Inject constructor(
        private val getLastWorkoutResultUC: GetLastWorkoutResultUC
) : SimpleVM<WorkoutResult>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(getLastWorkoutResultUC()))
    }
}