package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.repository.ExerciseRepository
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.model.FullExercise
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExerciseVM @Inject constructor(
        private val repository: ExerciseRepository
) : BaseViewModel<FullExercise, Throwable>() {

    fun fetch(id: Long) {
        disposables.add(
                repository.fetchFullExerciseInfo(id)
                        .subscribeOn(Schedulers.io())
                        .subscribe({ item ->
                            pushItem(item)
                        }, { error ->
                            pushError(error)
                        })
        )
    }
}