package com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.GetCurrentWorkoutExerciseUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.GetWorkoutExerciseUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutExerciseVM @Inject constructor(
        private val getWorkoutExerciseUC: GetWorkoutExerciseUC,
        private val getCurrentWorkoutExerciseUC: GetCurrentWorkoutExerciseUC
) : BaseViewModel<WorkoutExercise, Throwable>() {

    fun fetch(exerciseID: Long) {
        pushItem(getWorkoutExerciseUC(exerciseID))
    }

    fun fetch() {
        disposables.add(
                getCurrentWorkoutExerciseUC()
                        .subscribeOn(Schedulers.io())
                        .subscribe({ exercise ->
                            pushItem(exercise)
                        }, { error ->
                            pushError(error)
                        })
        )
    }
}