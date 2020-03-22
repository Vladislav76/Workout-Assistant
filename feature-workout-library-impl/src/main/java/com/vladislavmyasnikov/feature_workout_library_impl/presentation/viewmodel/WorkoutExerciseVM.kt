package com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.GetWorkoutExerciseUC
import javax.inject.Inject

class WorkoutExerciseVM @Inject constructor(
        private val getWorkoutExerciseUC: GetWorkoutExerciseUC
) : BaseViewModel<WorkoutExercise, Throwable>() {

    override val label = "WORKOUT_EXERCISE_VM"

    fun fetch(exerciseID: Long) {
        pushItem(getWorkoutExerciseUC(exerciseID))
    }
}