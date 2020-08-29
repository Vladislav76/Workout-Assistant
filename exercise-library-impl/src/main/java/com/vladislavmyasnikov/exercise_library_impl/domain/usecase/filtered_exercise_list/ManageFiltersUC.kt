package com.vladislavmyasnikov.exercise_library_impl.domain.usecase.filtered_exercise_list

import io.reactivex.Observable

interface ManageFiltersUC {

    fun getCurrentFilters(): Observable<Pair<List<String>, List<Boolean>>>
    fun selectMuscleGroup(position: Int, isSelected: Boolean)
    fun applySelectedFilters()
    fun cancelSelectedFilters()
    fun clearAllFilters()
}