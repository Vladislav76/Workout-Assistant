package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.GetWorkoutExerciseUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller.GetSelectedWorkoutExerciseUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutExerciseVM @Inject constructor(
        private val getSelectedWorkoutExerciseUC: GetSelectedWorkoutExerciseUC,
        private val getWorkoutExerciseUC: GetWorkoutExerciseUC
) : BaseViewModel<WorkoutExercise, Throwable>() {

    fun fetch(exerciseID: Long) {
        pushItem(getSelectedWorkoutExerciseUC(exerciseID))
    }

    fun fetch() {
        disposables.add(
                getWorkoutExerciseUC()
                        .subscribeOn(Schedulers.io())
                        .subscribe({ exercise ->
                            pushItem(exercise)
                        }, { error ->
                            pushError(error)
                        })
        )
    }
}