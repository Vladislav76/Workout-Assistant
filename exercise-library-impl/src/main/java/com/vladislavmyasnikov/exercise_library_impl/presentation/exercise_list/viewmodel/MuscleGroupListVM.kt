package com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.exercise_library_impl.domain.usecase.filtered_exercise_list.ManageFiltersUC
import io.reactivex.Completable
import javax.inject.Inject

class MuscleGroupListVM @Inject constructor(
        private val manageFiltersUC: ManageFiltersUC
) : SimpleVM<Pair<List<String>, List<Boolean>>>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(manageFiltersUC.getCurrentFilters()))
    }

    fun selectMuscleGroup(position: Int, isSelected: Boolean) {
        manageFiltersUC.selectMuscleGroup(position, isSelected)
    }

    fun apply() {
        manageFiltersUC.applySelectedFilters()
    }

    fun cancel() {
        manageFiltersUC.cancelSelectedFilters()
    }

    fun clearAll() {
        manageFiltersUC.clearAllFilters()
    }
}