package com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.exercise_library_impl.domain.entity.ShortExercise
import com.vladislavmyasnikov.exercise_library_impl.domain.usecase.filtered_exercise_list.GetFilteredExerciseListUC
import io.reactivex.Completable
import javax.inject.Inject

class ExerciseListVM @Inject constructor(
        private val getFilteredExerciseListUC: GetFilteredExerciseListUC
) : SimpleVM<List<ShortExercise>>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(getFilteredExerciseListUC.getFilteredExercises()))
    }
}