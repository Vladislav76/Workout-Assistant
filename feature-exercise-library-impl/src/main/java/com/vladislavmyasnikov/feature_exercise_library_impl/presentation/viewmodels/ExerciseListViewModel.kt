package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels

import com.vladislavmyasnikov.core_components.components.GeneralViewModel
import com.vladislavmyasnikov.core_utils.utils.utils.Logger
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ExerciseRepository
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ShortExerciseInfo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExerciseListViewModel @Inject constructor(private val repository: ExerciseRepository) : GeneralViewModel<Int>() {

    lateinit var exercisesInfo: List<ShortExerciseInfo>
        private set

    fun fetchShortExercisesInfo() {
        Logger.debug(TAG, "Exercises fetching: REQUEST")
        if (!isLoading) {
            progressEmitter.onNext(true)
            isLoading = true
            Logger.debug(TAG, "Exercises fetching: LOADING IS STARTED")

            disposables.add(repository.fetchShortExercisesInfo()
                    .doFinally {
                        progressEmitter.onNext(false)
                        isLoading = false
                        Logger.debug(TAG, "Exercises fetching: LOADING IS COMPLETED")
                    }
                    .subscribeOn(Schedulers.io())
                    .subscribe({ item ->
                        exercisesInfo = item
                        itemEmitter.onNext(LOADED_REQUEST_RESULT)
                        Logger.debug(TAG, "Exercises fetching: SUCCESS, Amount: ${item.size}")
                    }, { error ->
                        errorEmitter.onNext(error)//(ExceptionMapper.map(error))
                        Logger.debug(TAG, "Exercises fetching: ERROR, Cause: $error")
                    })
            )
        }
    }

    companion object {
        private const val TAG = "EXERCISE_LIST_VM"
    }
}