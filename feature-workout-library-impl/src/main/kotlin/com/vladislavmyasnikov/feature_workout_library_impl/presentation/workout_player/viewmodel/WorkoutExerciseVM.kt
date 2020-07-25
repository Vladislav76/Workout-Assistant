package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.GetWorkoutExerciseUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller.GetSelectedWorkoutExerciseUC
import io.reactivex.Completable
import javax.inject.Inject

class WorkoutExerciseVM @Inject constructor(
        private val getSelectedWorkoutExerciseUC: GetSelectedWorkoutExerciseUC,
        private val getWorkoutExerciseUC: GetWorkoutExerciseUC
) : SimpleVM<WorkoutExercise>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        if (id < 0) {
            return Either.Right(initAsynchronousRequest(getWorkoutExerciseUC()))
        } else {
            pushItem(getSelectedWorkoutExerciseUC(id))
            return Either.Left(true)
        }
    }
}