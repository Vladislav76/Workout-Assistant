package com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutSetConfig
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.GetWorkoutSetConfigUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.ChangeWorkoutSetConfigUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutSetConfigVM @Inject constructor(
        private val getWorkoutSetConfigUC: GetWorkoutSetConfigUC,
        private val changeWorkoutSetConfigUC: ChangeWorkoutSetConfigUC
) : BaseViewModel<WorkoutSetConfig, Throwable>() {

    override val label = "WORKOUT_SET_CONFIG_VM"

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
        changeWorkoutSetConfigUC.setNumber(number)
    }

    fun updateWorkoutSetApproach(approach: Int) {
        changeWorkoutSetConfigUC.setApproach(approach)
    }
}