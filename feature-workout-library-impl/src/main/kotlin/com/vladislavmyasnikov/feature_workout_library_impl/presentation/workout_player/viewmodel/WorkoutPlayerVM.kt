package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutProcessState
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.ManageWorkoutProcessUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.SaveWorkoutResultUC
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutPlayerVM @Inject constructor(
        private val manageWorkoutProcessUC: ManageWorkoutProcessUC,
        private val saveWorkoutResultUC: SaveWorkoutResultUC
) : SimpleVM<WorkoutProcessState>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        manageWorkoutProcessUC.startWorkoutById(id)
        return Either.Right(initAsynchronousRequest(manageWorkoutProcessUC.getCurrentWorkoutProcessState()))
    }

    fun stop() {
        manageWorkoutProcessUC.stopWorkout()
        saveWorkoutResultUC.saveCurrentWorkoutResult()
                .subscribeOn(Schedulers.io())
                .subscribe { pushItem(WorkoutProcessState.FINISHED) }
                .also { disposable -> disposables.add(disposable) }
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