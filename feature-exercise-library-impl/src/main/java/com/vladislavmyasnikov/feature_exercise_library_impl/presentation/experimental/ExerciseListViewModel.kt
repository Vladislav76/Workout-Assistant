package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental

import com.vladislavmyasnikov.common.experimental.BaseViewModel
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ExerciseRepository
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ShortExerciseInfo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExerciseListViewModel @Inject constructor(private val repository: ExerciseRepository) : BaseViewModel<List<ShortExerciseInfo>, Throwable>() {

    fun fetch() {
        disposables.add(
                repository.fetchShortExercisesInfo()
                        .subscribeOn(Schedulers.io())
                        .subscribe({ item ->
                            pushItem(item)
                        }, { error ->
                            pushError(error)
                        })
        )
    }
}