package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutSetConfig
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller.GetWorkoutSetConfigUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller.ChangeWorkoutSetConfigUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutSetConfigVM @Inject constructor(
        private val getWorkoutSetConfigUC: GetWorkoutSetConfigUC,
        private val changeWorkoutSetConfigUC: ChangeWorkoutSetConfigUC
) : BaseViewModel<WorkoutSetConfig, Throwable>() {

    fun fetch() {
        disposables.add(
                getWorkoutSetConfigUC()
                        .subscribeOn(Schedulers.io())
                        .subscribe({ config ->
                            pushItem(config)
                        }, { error ->
                            pushError(error)
                        })
        )
    }

    fun updateWorkoutSetNumber(number: Int) {
        changeWorkoutSetConfigUC.putSetIndex(number)
    }

    fun updateWorkoutSetApproach(approach: Int) {
        changeWorkoutSetConfigUC.putApproachIndex(approach)
    }
}