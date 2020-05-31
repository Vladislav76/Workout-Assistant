package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel

import android.os.Handler
import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.TimerValue
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.ChangeWorkoutProcessStateUC
import javax.inject.Inject

class WorkoutPlayerVM @Inject constructor(
        private val changeWorkoutProcessStateUC: ChangeWorkoutProcessStateUC
) : BaseViewModel<TimerValue, Throwable>() {

    private var timer: Int = 0
    private val handler = Handler()
    private var isRunning = false

    private val incrementTimer = object : Runnable {
        override fun run() {
            if (isRunning) {
                timer++
                val hours = timer / 3600
                val minutes = timer % 3600 / 60
                val seconds = timer % 60
                pushItem(TimerValue(hours, minutes, seconds))
            }
            handler.postDelayed(this, 1000)
        }
    }

    fun start(workoutPlanID: Long) {
        changeWorkoutProcessStateUC.start(workoutPlanID)
        isRunning = true
        incrementTimer.run()
    }

    fun stop() {
        changeWorkoutProcessStateUC.stop()
        isRunning = true
    }

    fun pause() {
        changeWorkoutProcessStateUC.pause()
        isRunning = false
    }

    fun resume() {
        changeWorkoutProcessStateUC.resume()
        isRunning = true
    }

    fun next() {
        changeWorkoutProcessStateUC.next()
    }
}