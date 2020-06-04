package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutProcessState
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.AccessWorkoutProcessStateUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutPlayerVM @Inject constructor(
        private val accessWorkoutProcessStateUC: AccessWorkoutProcessStateUC
) : BaseViewModel<WorkoutProcessState, Throwable>() {

    fun start(workoutPlanID: Long) {
        accessWorkoutProcessStateUC.startWorkout(workoutPlanID)

        // TODO: how to remove this boilerplate code?
        disposables.add(
                accessWorkoutProcessStateUC.getWorkoutProcessState()
                        .subscribeOn(Schedulers.io()) // TODO: need this or not?
                        .subscribe({ item ->
                            pushItem(item)
                        }, { error ->
                            pushError(error)
                        })
        )
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