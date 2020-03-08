package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ExerciseRepository
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.FullExerciseInfo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExerciseVM @Inject constructor(private val repository: ExerciseRepository) : BaseViewModel<FullExerciseInfo, Throwable>() {

    override val label = "EXERCISE_VM"

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