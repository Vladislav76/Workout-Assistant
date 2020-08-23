package com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_details.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.exercise_library_impl.domain.entity.Exercise
import com.vladislavmyasnikov.exercise_library_impl.domain.usecase.exercise_details.GetExerciseUC
import io.reactivex.Completable
import javax.inject.Inject

class ExerciseVM @Inject constructor(
        private val getExerciseUC: GetExerciseUC
) : SimpleVM<Exercise>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(getExerciseUC.getExerciseById(id)))
    }
}