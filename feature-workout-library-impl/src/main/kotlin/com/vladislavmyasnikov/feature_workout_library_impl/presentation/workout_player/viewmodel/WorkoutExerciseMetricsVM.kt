package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExerciseIndicators
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.AccessWorkoutExerciseMetricsUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutExerciseMetricsVM @Inject constructor(
        private val accessWorkoutExerciseMetricsUC: AccessWorkoutExerciseMetricsUC
) : BaseViewModel<WorkoutExerciseIndicators, Throwable>() {

    private lateinit var currentData: WorkoutExerciseIndicators

    fun fetch() {
        disposables.add(
                accessWorkoutExerciseMetricsUC.getMetrics()
                        .subscribeOn(Schedulers.io())
                        .subscribe({ data ->
                            pushItem(data)
                            currentData = data
                        }, { error ->
                            pushError(error)
                        })
        )
    }

    fun increaseReps() {
        if (currentData.reps < 1000) {
            accessWorkoutExerciseMetricsUC.setRepetitionMetrics(currentData.reps + 1)
        }
    }

    fun decreaseReps() {
        if (currentData.reps > 0) {
            accessWorkoutExerciseMetricsUC.setRepetitionMetrics(currentData.reps - 1)
        }
    }

    fun increaseWeight() {
        if (currentData.weight < 1000F) {
            accessWorkoutExerciseMetricsUC.setWeightMetrics(currentData.weight + 1F)
        }
    }

    fun decreaseWeight() {
        if (currentData.weight > 1F) {
            accessWorkoutExerciseMetricsUC.setWeightMetrics(currentData.weight - 1F)
        } else if (currentData.weight > 0F) {
            accessWorkoutExerciseMetricsUC.setWeightMetrics(0F)
        }
    }
}