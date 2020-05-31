package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExerciseConfig
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.GetWorkoutExerciseConfigUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutExerciseConfigVM @Inject constructor(
        private val getWorkoutExerciseConfigUC: GetWorkoutExerciseConfigUC
) : BaseViewModel<WorkoutExerciseConfig, Throwable>() {

    fun fetch() {
        disposables.add(
                getWorkoutExerciseConfigUC()
                        .subscribeOn(Schedulers.io())
                        .subscribe({ config ->
                            pushItem(config)
                        }, { error ->
                            pushError(error)
                        })
        )
    }
}