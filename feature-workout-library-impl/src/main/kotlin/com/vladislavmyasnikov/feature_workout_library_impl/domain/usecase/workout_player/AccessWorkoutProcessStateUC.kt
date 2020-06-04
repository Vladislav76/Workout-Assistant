package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutProcessState
import io.reactivex.Observable

interface AccessWorkoutProcessStateUC {

    fun getWorkoutProcessState(): Observable<WorkoutProcessState>
    fun startWorkout(workoutPlanID: Long)
    fun stopWorkout()
    fun pauseWorkout()
    fun resumeWorkout()
    fun nextExercise()
}