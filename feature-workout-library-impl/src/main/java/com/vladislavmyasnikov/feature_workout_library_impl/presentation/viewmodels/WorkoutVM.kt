package com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.domain.FullWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutRepository
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutVM @Inject constructor(private val repository: WorkoutRepository) : BaseViewModel<FullWorkout, Throwable>() {

    override val label = "WORKOUT_VM"

    fun fetch(id: Long) {
        disposables.add(
                repository.fetchFullWorkout(id)
                        .subscribeOn(Schedulers.io())
                        .subscribe({ item ->
                            pushItem(item)
                        }, { error ->
                            pushError(error)
                        })
        )
    }
}