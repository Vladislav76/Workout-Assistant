package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.viewmodels

import com.vladislavmyasnikov.common.experimental.BaseViewModel
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ExerciseRepository
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ShortExerciseInfo
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable
import javax.inject.Inject

class ExerciseListViewModel @Inject constructor(private val repository: ExerciseRepository) : BaseViewModel<List<ShortExerciseInfo>, Throwable>() {

    private var sourceItems: List<ShortExerciseInfo> = emptyList()

    var filteredMuscleGroupIDs: List<Int> = emptyList()
        private set

    fun fetch() {
        if (filteredMuscleGroupIDs.isEmpty()) {
            disposables.add(
                    repository.fetchShortExercisesInfo()
                            .subscribeOn(Schedulers.io())
                            .subscribe({ item ->
                                pushItem(item)
                                sourceItems = item
                            }, { error ->
                                pushError(error)
                            })
            )
        }
    }

    fun filter(ids: List<Int>) {
        filteredMuscleGroupIDs = ids

        val filteredItems =
                if (ids.isNotEmpty())
                    sourceItems.filter { item ->
                        item.muscleGroupsIDs.any { id ->
                            id in ids
                        }
                    }
                else sourceItems

        disposables.add(
                Single.fromCallable { filteredItems }
                        .subscribeOn(Schedulers.io())
                        .subscribe { item ->
                            pushItem(item)
                        }
        )
    }
}