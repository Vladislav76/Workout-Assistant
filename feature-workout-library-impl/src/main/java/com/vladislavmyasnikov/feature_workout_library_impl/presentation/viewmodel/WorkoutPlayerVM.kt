package com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.ManageExecutingWorkoutUC
import javax.inject.Inject

class WorkoutPlayerVM @Inject constructor(
        private val manageExecutingWorkoutUC: ManageExecutingWorkoutUC
) : BaseViewModel<Nothing, Throwable>() {

    fun start(workoutPlanID: Long) {
        manageExecutingWorkoutUC.start(workoutPlanID)
    }

    fun stop() {
        manageExecutingWorkoutUC.stop()
    }

    fun pause() {
        manageExecutingWorkoutUC.pause()
    }

    fun resume() {
        manageExecutingWorkoutUC.resume()
    }

    fun next() {
        manageExecutingWorkoutUC.next()
    }
}