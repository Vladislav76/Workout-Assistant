package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.TimerValue
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.GetWorkoutTimerValueUC
import io.reactivex.Completable
import javax.inject.Inject

class WorkoutTimerVM @Inject constructor(
        private val getWorkoutTimerValueUC: GetWorkoutTimerValueUC
) : SimpleVM<TimerValue>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(getWorkoutTimerValueUC.currentTimer()))
    }
}