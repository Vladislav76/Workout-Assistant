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
        getExerciseListUC: GetExerciseListUC
) : GetFilteredExerciseListUC, ManageFiltersUC {

    private lateinit var sourceItems: List<ShortExercise>
    private val filteredExerciseListSubject = BehaviorSubject.create<List<ShortExercise>>()
    private val disposables = CompositeDisposable()

    // TODO: fetch from repository (database)
    private val muscleGroupNames = listOf("Chest", "Back", "Biceps", "Triceps", "Shoulders", "Trapezius", "Quadriceps", "Calves", "Abs")
    private var lastAppliedFilters = mutableListOf(false, false, false, false, false, false, false, false, false)
    private var currentFilters = mutableListOf(false, false, false, false, false, false, false, false, false)
    private val currentFiltersSubject = BehaviorSubject.create<Pair<List<String>, List<Boolean>>>()

    init {
        getExerciseListUC.invoke()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { item -> sourceItems = item; filterItems() },
                        { error -> }
                )
                .also { disposables.add(it) }

        currentFiltersSubject.onNext(muscleGroupNames to currentFilters)
    }

    override fun getFilteredExercises(): Observable<List<ShortExercise>> = filteredExerciseListSubject

    override fun getCurrentFilters(): Observable<Pair<List<String>, List<Boolean>>> = currentFiltersSubject

    override fun selectMuscleGroup(position: Int, isSelected: Boolean) {
        currentFilters[position] = isSelected
    }

    override fun applySelectedFilters() {
        for (i in 0 until lastAppliedFilters.size) {
            lastAppliedFilters[i] = currentFilters[i]
        }
        filterItems()
    }

    override fun cancelSelectedFilters() {
        for (i in 0 until lastAppliedFilters.size) {
            currentFilters[i] = lastAppliedFilters[i]
        }
    }

    override fun clearAllFilters() {
        for (i in 0 until lastAppliedFilters.size) {
            lastAppliedFilters[i] = false
            currentFilters[i] = false
        }
        filterItems()
    }

    private fun filterItems() {
        val positions = lastAppliedFilters.withIndex().filter { it.value }.map { it.index }
        if (positions.isEmpty()) {
            filteredExerciseListSubject.onNext(sourceItems)
        } else {
            val filteredItems = sourceItems.filter { it.muscleGroupsIDs.any { id -> id in positions } }
            filteredExerciseListSubject.onNext(filteredItems)
        }
    }
}