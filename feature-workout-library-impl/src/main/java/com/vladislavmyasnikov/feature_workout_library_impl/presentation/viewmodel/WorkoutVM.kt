package com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.FullWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.GetWorkoutPlanUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutVM @Inject constructor(
        private val getWorkoutPlanUC: GetWorkoutPlanUC
) : BaseViewModel<FullWorkout, Throwable>() {

    override val label = "WORKOUT_VM"

    fun fetch(id: Long) {
        disposables.add(
                getWorkoutPlanUC(id)
                        .subscribeOn(Schedulers.io())
                        .subscribe({ item ->
                            pushItem(item)
                        }, { error ->
                            pushError(error)
                        })
        )
    }
}