package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel

import android.os.Handler
import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.TimerValue
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutProcessState
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.AccessWorkoutProcessStateUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutTimerVM @Inject constructor(
        private val accessWorkoutProcessStateUC: AccessWorkoutProcessStateUC
) : BaseViewModel<TimerValue, Throwable>() {

    private var timer: Int = 0
    private val handler = Handler()
    private var isResumed = false
    private var isStopped = false

    private val incrementTimer = object : Runnable {
        override fun run() {
            if (isResumed) {
                timer++
                val hours = timer / 3600
                val minutes = timer % 3600 / 60
                val seconds = timer % 60
                pushItem(TimerValue(hours, minutes, seconds))
            }
            if (!isStopped) {
                handler.postDelayed(this, 1000)
            }
        }
    }

    fun fetch() {
        disposables.add(
                accessWorkoutProcessStateUC.getWorkoutProcessState()
                        .subscribeOn(Schedulers.io())
                        .subscribe({ item ->
                            when (item) {
                                WorkoutProcessState.STARTED -> { isResumed = true }
                                WorkoutProcessState.PAUSED -> { isResumed = false }
                                WorkoutProcessState.FINISHED -> { isResumed = false; isStopped = true }
                            }
                        }, { error ->
                            pushError(error)
                        })
        )
        handler.postDelayed(incrementTimer, 1000)
    }
}