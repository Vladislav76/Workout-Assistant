package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_details.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutExerciseCycle
import io.reactivex.Completable
import javax.inject.Inject

class WorkoutExerciseCycleListVM @Inject constructor(

) : SimpleVM<List<WorkoutExerciseCycle>>() {

    private val dummyItem = listOf(
                            WorkoutExerciseCycle(0L, 10, 15F),
                            WorkoutExerciseCycle(0L, 20, 25F),
                            WorkoutExerciseCycle(0L, 10, 15F),
                            WorkoutExerciseCycle(0L, 20, 25F),
    )

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        pushItem(dummyItem)
        return Either.Left(true)
    }
}