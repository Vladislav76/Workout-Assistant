package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_creation

import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutExerciseIndicators
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutExercise
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutExerciseCycle
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutSet
import io.reactivex.Observable

interface AccessToWorkoutDataUC {

    fun getAllSets(): Observable<List<WorkoutSet>>

    fun getExercisesBySetId(id: Long): Observable<List<WorkoutExercise>>

    fun getCyclesByExerciseId(id: Long): Observable<List<WorkoutExerciseCycle>>

    fun getCycleById(id: Long): WorkoutExerciseCycle
    fun updateCycleById(id: Long, updatedData: WorkoutExerciseIndicators)
}