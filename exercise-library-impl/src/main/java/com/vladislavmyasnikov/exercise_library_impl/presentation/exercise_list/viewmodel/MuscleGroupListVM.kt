package com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.exercise_library_impl.domain.usecase.filtered_exercise_list.ApplyFilterUC
import com.vladislavmyasnikov.exercise_library_impl.domain.usecase.filtered_exercise_list.CheckMuscleGroupUC
import com.vladislavmyasnikov.exercise_library_impl.domain.usecase.filtered_exercise_list.GetMuscleGroupListUC
import io.reactivex.Completable
import javax.inject.Inject

class MuscleGroupListVM @Inject constructor(
        private val getMuscleGroupListUC: GetMuscleGroupListUC,
        private val checkMuscleGroupUC: CheckMuscleGroupUC,
        private val applyFilterUC: ApplyFilterUC
) : SimpleVM<List<Pair<String, Boolean>>>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(getMuscleGroupListUC.getAllMuscleGroups()))
    }

    fun checkItem(position: Int, isChecked: Boolean) {
        checkMuscleGroupUC.checkMuscleGroup(position, isChecked)
    }

    fun apply() {
        applyFilterUC.applyFiltering()
    }

    fun cancel() {
        // TBD
    }
}