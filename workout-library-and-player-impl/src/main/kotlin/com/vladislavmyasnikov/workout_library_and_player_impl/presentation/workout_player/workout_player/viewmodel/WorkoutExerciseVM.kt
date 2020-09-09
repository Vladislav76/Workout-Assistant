package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_player.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutExercise
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_player.GetCurrentWorkoutExerciseUC
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_set_controller.GetWorkoutExerciseUC
import io.reactivex.Completable
import javax.inject.Inject

class WorkoutExerciseVM @Inject constructor(
        private val getWorkoutExerciseUC: GetWorkoutExerciseUC,
        private val getCurrentWorkoutExerciseUC: GetCurrentWorkoutExerciseUC
) : SimpleVM<WorkoutExercise>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return if (id < 0) {
            Either.Right(initAsynchronousRequest(getCurrentWorkoutExerciseUC.getCurrentWorkoutExercise()))
        } else {
            pushItem(getWorkoutExerciseUC.getWorkoutExerciseById(id))
            Either.Left(true)
        }
    }
}