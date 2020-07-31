package com.vladislavmyasnikov.feature_workout_library_impl.domain.entity

data class WorkoutSetData(private val exerciseAmount: Int, private val approachAmount: Int) {

    private val dataTable = Array(exerciseAmount) { Array(approachAmount) { WorkoutExerciseIndicators(0, 0F) } }
    private val exerciseIndexRange = 0 until exerciseAmount
    private val approachIndexRange = 0 until approachAmount

    fun getExerciseApproachData(exerciseIndex: Int, approachIndex: Int): WorkoutExerciseIndicators {
        if (exerciseIndex in exerciseIndexRange && approachIndex in approachIndexRange) {
            return dataTable[exerciseIndex][approachIndex]
        }
        return WorkoutExerciseIndicators(0, 0F)
    }
}