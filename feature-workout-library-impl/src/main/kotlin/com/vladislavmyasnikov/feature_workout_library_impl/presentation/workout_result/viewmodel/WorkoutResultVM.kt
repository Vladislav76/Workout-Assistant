package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_result.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.BaseVM

class WorkoutResultVM : BaseVM<Unit, Throwable>() {

    fun fetch() {
        pushItem(Unit)
    }
}