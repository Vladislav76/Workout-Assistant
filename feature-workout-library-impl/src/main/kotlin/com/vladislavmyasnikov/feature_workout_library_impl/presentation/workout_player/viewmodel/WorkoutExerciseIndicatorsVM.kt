package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExerciseIndicators
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.GetWorkoutExerciseIndicatorsUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.SetWorkoutExerciseIndicatorsUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutExerciseIndicatorsVM @Inject constructor(
        private val getWorkoutExerciseIndicatorsUC: GetWorkoutExerciseIndicatorsUC,
        private val setWorkoutExerciseIndicatorsUC: SetWorkoutExerciseIndicatorsUC
) : BaseViewModel<WorkoutExerciseIndicators, Throwable>() {

    private lateinit var currentData: WorkoutExerciseIndicators

    fun fetch() {
        disposables.add(
                getWorkoutExerciseIndicatorsUC()
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
            setWorkoutExerciseIndicatorsUC.setRepetitions(currentData.reps + 1)
        }
    }

    fun decreaseReps() {
        if (currentData.reps > 0) {
            setWorkoutExerciseIndicatorsUC.setRepetitions(currentData.reps - 1)
        }
    }

    fun increaseWeight() {
        if (currentData.weight < 1000F) {
            setWorkoutExerciseIndicatorsUC.setWeight(currentData.weight + 1F)
        }
    }

    fun decreaseWeight() {
        if (currentData.weight > 1F) {
            setWorkoutExerciseIndicatorsUC.setWeight(currentData.weight - 1F)
        } else if (currentData.weight > 0F) {
            setWorkoutExerciseIndicatorsUC.setWeight(0F)
        }
    }
}