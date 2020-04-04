package com.vladislavmyasnikov.feature_workout_library_impl.domain.model

data class WorkoutSetData(private val exerciseAmount: Int, private val approachAmount: Int) {

    private val dataTable = Array(exerciseAmount) { Array(approachAmount) { ExerciseApproachData(0, 0F) } }
    private val exerciseIndexRange = 0 until exerciseAmount
    private val approachIndexRange = 0 until approachAmount

    fun getExerciseApproachData(exerciseIndex: Int, approachIndex: Int): ExerciseApproachData {
        if (exerciseIndex in exerciseIndexRange && approachIndex in approachIndexRange) {
            return dataTable[exerciseIndex][approachIndex]
        }
        return ExerciseApproachData(0, 0F)
    }
}