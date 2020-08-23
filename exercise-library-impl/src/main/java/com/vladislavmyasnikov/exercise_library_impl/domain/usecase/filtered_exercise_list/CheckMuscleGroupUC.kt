package com.vladislavmyasnikov.exercise_library_impl.domain.usecase.filtered_exercise_list

interface CheckMuscleGroupUC {

    fun checkMuscleGroup(position: Int, isChecked: Boolean)
}