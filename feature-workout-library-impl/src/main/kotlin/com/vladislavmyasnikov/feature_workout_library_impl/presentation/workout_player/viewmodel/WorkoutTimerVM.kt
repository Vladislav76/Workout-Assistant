package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.TimerValue
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.GetWorkoutProcessStateUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutTimerVM @Inject constructor(
        private val getWorkoutProcessStateUC: GetWorkoutProcessStateUC
) : BaseViewModel<TimerValue, Throwable>() {

    fun fetch() {
        disposables.add(
                getWorkoutProcessStateUC()
                        .subscribeOn(Schedulers.io())
                        .subscribe({ item ->
//                            pushItem(item)
                        }, { error ->
                            pushError(error)
                        })
        )
    }
}