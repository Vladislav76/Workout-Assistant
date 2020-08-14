package com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_list.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.ShortCompletedWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.completed_workout_list.GetCompletedWorkoutListUC
import io.reactivex.Completable
import javax.inject.Inject

class CompletedWorkoutListVM @Inject constructor(
        private val getCompletedWorkoutListUC: GetCompletedWorkoutListUC
) : SimpleVM<List<ShortCompletedWorkout>>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(getCompletedWorkoutListUC.getAllCompletedWorkouts()))
    }
}