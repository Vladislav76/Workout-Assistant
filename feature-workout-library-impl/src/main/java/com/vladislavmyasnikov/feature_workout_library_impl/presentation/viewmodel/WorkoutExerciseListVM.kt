package com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExerciseInfo
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.GetCurrentWorkoutExercisesUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutExerciseListVM @Inject constructor(
        private val getCurrentWorkoutExercisesUC: GetCurrentWorkoutExercisesUC
) : BaseViewModel<List<WorkoutExercise>, Throwable>() {

    override val label = "WORKOUT_EXERCISE_LIST_VM"

    fun fetch(workoutPlanID: Long) {
        disposables.add(
                getCurrentWorkoutExercisesUC(workoutPlanID)
                        .subscribeOn(Schedulers.io())
                        .subscribe({ exercises ->
                            pushItem(exercises)
                        }, { error ->
                            pushError(error)
                        })
        )
    }
}