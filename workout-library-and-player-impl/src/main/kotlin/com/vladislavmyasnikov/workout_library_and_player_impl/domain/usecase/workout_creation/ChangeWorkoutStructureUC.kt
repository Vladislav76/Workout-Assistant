package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_creation

interface ChangeWorkoutStructureUC {

    fun createEmptyWorkout()

    fun addEmptySet(cycleAmount: Int): Long
    fun removeSetById(id: Long)

    fun addExerciseToSet(setId: Long, exerciseId: Long)
    fun removeExerciseFromSet(setId: Long, exerciseId: Long)

    fun addCycleToSet(setId: Long)
    fun removeLastCycleFromSet(setId: Long)

    fun addCyclesToSet(setId: Long, cycleAmount: Int)
    fun removeLastCyclesFromSet(setId: Long, cycleAmount: Int)
}