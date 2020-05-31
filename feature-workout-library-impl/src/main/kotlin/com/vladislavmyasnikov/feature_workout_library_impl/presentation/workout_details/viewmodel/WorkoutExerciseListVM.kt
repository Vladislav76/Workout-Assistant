package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller.GetWorkoutExerciseListUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller.RequestWorkoutUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutExerciseListVM @Inject constructor(
        private val requestWorkoutUC: RequestWorkoutUC,
        private val getWorkoutExerciseListUC: GetWorkoutExerciseListUC
) : BaseViewModel<List<WorkoutExercise>, Throwable>() {

    fun fetch(workoutPlanID: Long) {
        requestWorkoutUC(workoutPlanID)
        disposables.add(
                getWorkoutExerciseListUC()
                        .subscribeOn(Schedulers.io())
                        .subscribe({ exercises ->
                            pushItem(exercises)
                        }, { error ->
                            pushError(error)
                        })
        )
    }
}