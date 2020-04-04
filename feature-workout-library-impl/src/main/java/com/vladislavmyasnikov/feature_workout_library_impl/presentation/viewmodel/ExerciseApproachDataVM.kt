package com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.ExerciseApproachData
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.GetCurrentExerciseApproachDataUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.SetCurrentExerciseApproachDataUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExerciseApproachDataVM @Inject constructor(
        private val getCurrentExerciseApproachDataUC: GetCurrentExerciseApproachDataUC,
        private val setCurrentExerciseApproachDataUC: SetCurrentExerciseApproachDataUC
) : BaseViewModel<ExerciseApproachData, Throwable>() {

    override val label = "WORKOUT_EXERCISE_APPROACH_DATA_VM"

    private lateinit var currentData: ExerciseApproachData

    fun fetch() {
        disposables.add(
                getCurrentExerciseApproachDataUC()
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
            setCurrentExerciseApproachDataUC.setRepetitions(currentData.reps + 1)
        }
    }

    fun decreaseReps() {
        if (currentData.reps > 0) {
            setCurrentExerciseApproachDataUC.setRepetitions(currentData.reps - 1)
        }
    }

    fun increaseWeight() {
        if (currentData.weight < 1000F) {
            setCurrentExerciseApproachDataUC.setWeight(currentData.weight + 1F)
        }
    }

    fun decreaseWeight() {
        if (currentData.weight > 1F) {
            setCurrentExerciseApproachDataUC.setWeight(currentData.weight - 1F)
        } else if (currentData.weight > 0F) {
            setCurrentExerciseApproachDataUC.setWeight(0F)
        }
    }
}