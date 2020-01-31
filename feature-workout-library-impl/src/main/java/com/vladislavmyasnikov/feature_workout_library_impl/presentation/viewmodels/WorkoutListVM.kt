package com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.ShortWorkoutInfo
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutRepository
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutListVM @Inject constructor(private val repository: WorkoutRepository) : BaseViewModel<List<ShortWorkoutInfo>, Throwable>() {

    private var sourceItems: List<ShortWorkoutInfo> = emptyList()

    fun fetch() {
        disposables.add(
                repository.fetchShortWorkoutsInfo()
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