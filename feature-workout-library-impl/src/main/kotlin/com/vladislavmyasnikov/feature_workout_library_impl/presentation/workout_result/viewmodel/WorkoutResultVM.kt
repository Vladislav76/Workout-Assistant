package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_result.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutResult
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_result.GetLastSavedWorkoutResultUC
import io.reactivex.Completable
import javax.inject.Inject

class WorkoutResultVM @Inject constructor(
        private val getLastSavedWorkoutResultUC: GetLastSavedWorkoutResultUC
) : SimpleVM<WorkoutResult>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(getLastSavedWorkoutResultUC()))
    }
}