package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_exercise_details.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutExerciseCycle
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_creation.AccessToWorkoutDataUC
import io.reactivex.Completable
import javax.inject.Inject

class WorkoutExerciseCycleListVM @Inject constructor(
        private val accessToWorkoutDataUC: AccessToWorkoutDataUC
) : SimpleVM<List<WorkoutExerciseCycle>>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(accessToWorkoutDataUC.getCyclesByExerciseId(id)))
    }
}