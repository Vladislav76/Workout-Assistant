package com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.GetCurrentWorkoutExercisesUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.RequestWorkoutPlanInfoUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutExerciseListVM @Inject constructor(
        private val requestWorkoutPlanInfoUC: RequestWorkoutPlanInfoUC,
        private val getCurrentWorkoutExercisesUC: GetCurrentWorkoutExercisesUC
) : BaseViewModel<List<WorkoutExercise>, Throwable>() {

    fun fetch(workoutPlanID: Long) {
        requestWorkoutPlanInfoUC(workoutPlanID)
        disposables.add(
                getCurrentWorkoutExercisesUC()
                        .subscribeOn(Schedulers.io())
                        .subscribe({ exercises ->
                            pushItem(exercises)
                        }, { error ->
                            pushError(error)
                        })
        )
    }
}