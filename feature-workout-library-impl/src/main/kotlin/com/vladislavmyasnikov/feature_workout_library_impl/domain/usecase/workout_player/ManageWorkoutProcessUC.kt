package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player

import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutProcessState
import io.reactivex.Observable

interface ManageWorkoutProcessUC {

    fun getCurrentWorkoutProcessState(): Observable<WorkoutProcessState>
    fun startWorkoutById(id: Long)
    fun stopWorkout()
    fun pauseWorkout()
    fun resumeWorkout()
    fun nextExercise()
}