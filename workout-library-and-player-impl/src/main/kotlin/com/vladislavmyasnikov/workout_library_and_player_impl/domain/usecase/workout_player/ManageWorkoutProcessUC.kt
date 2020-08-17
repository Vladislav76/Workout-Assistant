package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_player

import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_execution.WorkoutProcessState
import io.reactivex.Observable

interface ManageWorkoutProcessUC {

    fun getCurrentWorkoutProcessState(): Observable<WorkoutProcessState>
    fun startWorkoutById(id: Long)
    fun stopWorkout()
    fun pauseWorkout()
    fun resumeWorkout()
    fun nextExercise()
}