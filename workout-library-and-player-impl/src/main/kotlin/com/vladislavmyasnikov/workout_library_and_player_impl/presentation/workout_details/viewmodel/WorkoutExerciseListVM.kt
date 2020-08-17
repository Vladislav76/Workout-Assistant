package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutExercise
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_set_controller.GetCurrentWorkoutExerciseListUC
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_set_controller.RequestWorkoutUC
import io.reactivex.Completable
import javax.inject.Inject

class WorkoutExerciseListVM @Inject constructor(
        private val requestWorkoutUC: RequestWorkoutUC,
        private val getCurrentWorkoutExerciseListUC: GetCurrentWorkoutExerciseListUC
) : SimpleVM<List<WorkoutExercise>>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        requestWorkoutUC.requestWorkoutById(id)
        return Either.Right(initAsynchronousRequest(getCurrentWorkoutExerciseListUC.getCurrentWorkoutExercises()))
    }
}