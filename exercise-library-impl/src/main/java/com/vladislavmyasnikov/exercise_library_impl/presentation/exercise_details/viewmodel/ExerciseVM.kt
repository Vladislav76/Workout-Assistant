package com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_details.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.BaseVM
import com.vladislavmyasnikov.exercise_library_impl.domain.repository.ExerciseRepository
import com.vladislavmyasnikov.exercise_library_impl.domain.entity.FullExercise
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExerciseVM @Inject constructor(
        private val repository: ExerciseRepository
) : BaseVM<FullExercise, Throwable>() {

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