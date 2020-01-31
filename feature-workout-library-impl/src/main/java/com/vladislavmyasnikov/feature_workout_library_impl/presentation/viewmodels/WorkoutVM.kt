package com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.FullWorkoutInfo
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutRepository
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutVM @Inject constructor(private val repository: WorkoutRepository) : BaseViewModel<FullWorkoutInfo, Throwable>() {

    fun fetch(id: Long) {
        disposables.add(
                repository.fetchFullWorkoutInfo(id)
                        .subscribeOn(Schedulers.io())
                        .subscribe({ item ->
                            pushItem(item)
                        }, { error ->
                            pushError(error)
                        })
        )
    }
}