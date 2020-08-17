package com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_list.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.workout_diary_impl.domain.usecase.completed_workout_list.GetCompletedWorkoutListUC
import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.ShortCompletedWorkout
import io.reactivex.Completable
import javax.inject.Inject

class CompletedWorkoutListVM @Inject constructor(
        private val getCompletedWorkoutListUC: GetCompletedWorkoutListUC
) : SimpleVM<List<ShortCompletedWorkout>>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(getCompletedWorkoutListUC.getAllCompletedWorkouts()))
    }
}