package com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.ShortWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.GetWorkoutPlansUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutListVM @Inject constructor(
        private val getWorkoutPlansUC: GetWorkoutPlansUC
) : BaseViewModel<List<ShortWorkout>, Throwable>() {

    override val label = "WORKOUT_LIST_VM"

    private var sourceItems: List<ShortWorkout> = emptyList()

    fun fetch() {
        disposables.add(
                getWorkoutPlansUC()
                        .subscribeOn(Schedulers.io())
                        .subscribe({ item ->
                            pushItem(item)
                            sourceItems = item
                        }, { error ->
                            pushError(error)
                        })
        )
    }
}