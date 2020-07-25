package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutProcessState
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.AccessWorkoutProcessStateUC
import io.reactivex.Completable
import javax.inject.Inject

class WorkoutPlayerVM @Inject constructor(
        private val accessWorkoutProcessStateUC: AccessWorkoutProcessStateUC
) : SimpleVM<WorkoutProcessState>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        accessWorkoutProcessStateUC.startWorkout(id)
        return Either.Right(initAsynchronousRequest(accessWorkoutProcessStateUC.getWorkoutProcessState()))
    }

    fun stop() {
        accessWorkoutProcessStateUC.stopWorkout()
    }

    fun pause() {
        accessWorkoutProcessStateUC.pauseWorkout()
    }

    fun resume() {
        accessWorkoutProcessStateUC.resumeWorkout()
    }

    fun next() {
        accessWorkoutProcessStateUC.nextExercise()
    }
}