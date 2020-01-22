package com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels

import com.vladislavmyasnikov.common.legacy.GeneralViewModel
import com.vladislavmyasnikov.common.utils.Logger
import com.vladislavmyasnikov.feature_workout_library_impl.domain.ShortWorkoutInfo
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutRepository
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutListViewModel @Inject constructor(private val repository: WorkoutRepository) : GeneralViewModel<Int>() {

    lateinit var workoutsInfo: List<ShortWorkoutInfo>
        private set

    init {
        fetchShortWorkoutsInfo()
    }

    fun fetchShortWorkoutsInfo() {
        Logger.debug(TAG, "Workouts fetching: REQUEST")
        disposables.add(repository.fetchShortWorkoutsInfo()
                .subscribeOn(Schedulers.io())
                .subscribe({ item ->
                    workoutsInfo = item
                    itemEmitter.onNext(LOADED_REQUEST_RESULT)
                    Logger.debug(TAG, "Workouts fetching: SUCCESS, Amount: ${item.size}")
                }, { error ->
                    errorEmitter.onNext(error)//(ExceptionMapper.map(error))
                    Logger.debug(TAG, "Workouts fetching: ERROR, Cause: $error")
                })
        )
    }

    companion object {
        private const val TAG = "WORKOUT_LIST_VM"
    }
}