package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout.Workout
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_details.GetWorkoutUC
import io.reactivex.Completable
import javax.inject.Inject

class WorkoutVM @Inject constructor(
        private val getWorkoutUC: GetWorkoutUC
) : SimpleVM<Workout>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(getWorkoutUC.getWorkoutById(id)))
    }
}