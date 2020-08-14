package com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_result.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.CompletedWorkoutResult
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.completed_workout_result.GetLastCompletedWorkoutResultUC
import io.reactivex.Completable
import javax.inject.Inject

class CompletedWorkoutResultVM @Inject constructor(
        private val getLastCompletedWorkoutResultUC: GetLastCompletedWorkoutResultUC
) : SimpleVM<CompletedWorkoutResult>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(getLastCompletedWorkoutResultUC()))
    }
}