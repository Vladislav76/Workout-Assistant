package com.vladislavmyasnikov.exercise_library_impl.domain.usecase.filtered_exercise_list

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.exercise_library_impl.domain.entity.ShortExercise
import com.vladislavmyasnikov.exercise_library_impl.domain.usecase.exercise_list.GetExerciseListUC
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@PerScreen
class FilterExerciseListUCImpl @Inject constructor(
        private val getExerciseListUC: GetExerciseListUC
) : GetMuscleGroupListUC, CheckMuscleGroupUC, GetFilteredExerciseListUC, ApplyFilterUC {

    // TODO: fetch from repository (database)
    private val originalItems = mutableListOf(
            "Chest" to false,
            "Back" to false,
            "Biceps" to false,
            "Triceps" to false,
            "Shoulders" to false,
            "Trapezius" to false,
            "Quadriceps" to false,
            "Calves" to false,
            "Abs" to false,
    )

    private lateinit var sourceItems: List<ShortExercise>
    private val filteredExerciseListSubject = BehaviorSubject.create<List<ShortExercise>>()
    private val disposables = CompositeDisposable()

    init {
        getExerciseListUC.invoke()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { item -> sourceItems = item; filterItems() },
                        { error -> }
                )
                .also { disposables.add(it) }
    }

    override fun getAllMuscleGroups(): Single<List<Pair<String, Boolean>>> {
        return Single.just(originalItems)
    }

    override fun checkMuscleGroup(position: Int, isChecked: Boolean) {
        originalItems[position] = originalItems[position].first to isChecked
    }

    override fun getFilteredExercises(): Observable<List<ShortExercise>> = filteredExerciseListSubject

    override fun applyFiltering() {
        filterItems()
    }

    private fun filterItems() {
        val positions = originalItems.withIndex().filter { it.value.second }.map { it.index }
        if (positions.isEmpty()) {
            filteredExerciseListSubject.onNext(sourceItems)
        } else {
            val filteredItems = sourceItems.filter { it.muscleGroupsIDs.any { id -> id in positions } }
            filteredExerciseListSubject.onNext(filteredItems)
        }
    }
}