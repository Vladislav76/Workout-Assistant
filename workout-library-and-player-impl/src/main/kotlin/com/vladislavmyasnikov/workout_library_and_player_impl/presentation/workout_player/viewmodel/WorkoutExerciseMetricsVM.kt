package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutExerciseIndicators
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_player.AccessWorkoutExerciseMetricsUC
import io.reactivex.Completable
import javax.inject.Inject

class WorkoutExerciseMetricsVM @Inject constructor(
        private val accessWorkoutExerciseMetricsUC: AccessWorkoutExerciseMetricsUC
) : SimpleVM<WorkoutExerciseIndicators>() {

    private lateinit var currentItem: WorkoutExerciseIndicators

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(accessWorkoutExerciseMetricsUC.getMetrics()))
    }

    override fun onSuccessfulResponse(item: WorkoutExerciseIndicators) {
        currentItem = item
    }

    fun increaseReps() {
        if (currentItem.reps < 1000) {
            accessWorkoutExerciseMetricsUC.setRepetitionMetrics(currentItem.reps + 1)
        }
    }

    fun decreaseReps() {
        if (currentItem.reps > 0) {
            accessWorkoutExerciseMetricsUC.setRepetitionMetrics(currentItem.reps - 1)
        }
    }

    fun increaseWeight() {
        if (currentItem.weight < 1000F) {
            accessWorkoutExerciseMetricsUC.setWeightMetrics(currentItem.weight + 1F)
        }
    }

    fun decreaseWeight() {
        if (currentItem.weight > 1F) {
            accessWorkoutExerciseMetricsUC.setWeightMetrics(currentItem.weight - 1F)
        } else if (currentItem.weight > 0F) {
            accessWorkoutExerciseMetricsUC.setWeightMetrics(0F)
        }
    }
}