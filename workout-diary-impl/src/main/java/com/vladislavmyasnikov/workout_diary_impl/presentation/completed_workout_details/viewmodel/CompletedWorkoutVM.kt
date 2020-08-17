package com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.workout_diary_impl.domain.usecase.completed_workout_details.GetCompletedWorkoutUC
import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.CompletedWorkout
import io.reactivex.Completable
import javax.inject.Inject

class CompletedWorkoutVM @Inject constructor(
        private val getCompletedWorkoutUC: GetCompletedWorkoutUC
) : SimpleVM<CompletedWorkout>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(getCompletedWorkoutUC.getCompletedWorkoutById(id)))
    }
}