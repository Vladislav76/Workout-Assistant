package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels

import com.vladislavmyasnikov.core_components.components.GeneralViewModel
import com.vladislavmyasnikov.core_utils.utils.utils.Logger
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ExerciseRepository
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.FullExerciseInfo
import io.reactivex.schedulers.Schedulers

class ExerciseViewModel(private val repository: ExerciseRepository) : GeneralViewModel<Int>() {

    lateinit var exerciseInfo: FullExerciseInfo
        private set

    fun fetchFullExerciseInfo(id: Long) {
        Logger.debug(TAG, "Exercise fetching: REQUEST")
        if (!isLoading) {
            progressEmitter.onNext(true)
            isLoading = true
            Logger.debug(TAG, "Exercise fetching: PROCESSING IS STARTED")

            disposables.add(repository.fetchFullExerciseInfo(id)
                    .doFinally {
                        progressEmitter.onNext(false)
                        isLoading = false
                        Logger.debug(TAG, "Exercise fetching: PROCESSING IS COMPLETED")
                    }
                    .subscribeOn(Schedulers.io())
                    .subscribe({ item ->
                        exerciseInfo = item
                        itemEmitter.onNext(LOADED_REQUEST_RESULT)
                        Logger.debug(TAG, "Exercise fetching: SUCCESS")
                    }, { error ->
                        errorEmitter.onNext(error)//(ExceptionMapper.map(error))
                        Logger.debug(TAG, "Exercise fetching: ERROR; Cause: $error")
                    })
            )
        }
    }

    companion object {
        private const val TAG = "EXERCISE_VM"
    }
}