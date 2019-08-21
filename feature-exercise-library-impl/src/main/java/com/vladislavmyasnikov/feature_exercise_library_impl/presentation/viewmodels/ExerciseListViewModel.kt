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

    init {
        fetchShortExercisesInfo()
    }

    fun fetchShortExercisesInfo() {
        Logger.debug(TAG, "Exercises fetching: REQUEST")
        disposables.add(repository.fetchShortExercisesInfo()
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

    companion object {
        private const val TAG = "EXERCISE_LIST_VM"
    }
}