package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_list.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.ShortWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_list.GetWorkoutListUC
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutListVM @Inject constructor(
        private val getWorkoutListUC: GetWorkoutListUC
) : BaseViewModel<List<ShortWorkout>, Throwable>() {

    private var sourceItems: List<ShortWorkout> = emptyList()

    fun fetch() {
        disposables.add(
                getWorkoutListUC()
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