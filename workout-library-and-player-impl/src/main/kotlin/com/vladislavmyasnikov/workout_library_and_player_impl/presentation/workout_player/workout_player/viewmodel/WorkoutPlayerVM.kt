package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_player.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_execution.WorkoutProcessState
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_player.ManageWorkoutProcessUC
import io.reactivex.Completable
import javax.inject.Inject

class WorkoutPlayerVM @Inject constructor(
        private val manageWorkoutProcessUC: ManageWorkoutProcessUC,
) : SimpleVM<WorkoutProcessState>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        manageWorkoutProcessUC.startWorkoutById(id)
        return Either.Right(initAsynchronousRequest(manageWorkoutProcessUC.getCurrentWorkoutProcessState()))
    }

    fun finish() {
        manageWorkoutProcessUC.finishWorkout()
    }

    fun pause() {
        manageWorkoutProcessUC.pauseWorkout()
    }

    fun resume() {
        manageWorkoutProcessUC.resumeWorkout()
    }

    fun next() {
        manageWorkoutProcessUC.nextExercise()
    }
}