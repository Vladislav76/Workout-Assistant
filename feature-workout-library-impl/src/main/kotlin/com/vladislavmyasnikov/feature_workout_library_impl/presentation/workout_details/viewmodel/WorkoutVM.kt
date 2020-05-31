package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.FullWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_details.GetWorkoutUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutVM @Inject constructor(
        private val getWorkoutUC: GetWorkoutUC
) : BaseViewModel<FullWorkout, Throwable>() {

    fun fetch(id: Long) {
        disposables.add(
                getWorkoutUC(id)
                        .subscribeOn(Schedulers.io())
                        .subscribe({ item ->
                            pushItem(item)
                        }, { error ->
                            pushError(error)
                        })
        )
    }
}