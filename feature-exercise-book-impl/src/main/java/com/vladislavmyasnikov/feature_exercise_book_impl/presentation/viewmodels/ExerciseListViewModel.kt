package com.vladislavmyasnikov.feature_exercise_book_impl.presentation.viewmodels

import com.vladislavmyasnikov.core_components.components.GeneralViewModel
import com.vladislavmyasnikov.feature_exercise_book_impl.domain.ExerciseRepository
import com.vladislavmyasnikov.feature_exercise_book_impl.domain.ShortExerciseInfo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExerciseListViewModel @Inject constructor(private val repository: ExerciseRepository) : GeneralViewModel<Int>() {

    lateinit var exercisesInfo: List<ShortExerciseInfo>
        private set

    fun fetchShortExercisesInfo() {
        if (!isLoading) {
            isLoading = true
            progressEmitter.onNext(true)
            disposables.add(repository.fetchShortExercisesInfo()
                    .doFinally {
                        progressEmitter.onNext(false)
                        isLoading = false
                    }
                    .subscribeOn(Schedulers.io())
                    .subscribe({ item ->
                        exercisesInfo = item
                        itemEmitter.onNext(LOADED_REQUEST_RESULT)
                    }, { error ->
                        errorEmitter.onNext(error)//(ExceptionMapper.map(error))
                    })
            )
        }
    }
}