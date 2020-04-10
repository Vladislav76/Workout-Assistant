package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.repository.ExerciseRepository
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.model.ShortExercise
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExerciseListVM @Inject constructor(
        private val repository: ExerciseRepository
) : BaseViewModel<List<ShortExercise>, Throwable>() {

    private var sourceItems: List<ShortExercise> = emptyList()

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