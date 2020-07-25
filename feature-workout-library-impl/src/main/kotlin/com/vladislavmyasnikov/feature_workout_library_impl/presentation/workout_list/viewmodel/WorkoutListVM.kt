package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_list.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.ShortWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_list.GetWorkoutListUC
import io.reactivex.Completable
import javax.inject.Inject

class WorkoutListVM @Inject constructor(
        private val getWorkoutListUC: GetWorkoutListUC
) : SimpleVM<List<ShortWorkout>>() {

    private var currentItem: List<ShortWorkout> = emptyList()

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(getWorkoutListUC()))
    }

    override fun onSuccessfulResponse(item: List<ShortWorkout>) {
        currentItem = item
    }
}