package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_list.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutExerciseIndicators
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutExercise
import io.reactivex.Completable
import javax.inject.Inject

class WorkoutExerciseListVM2 @Inject constructor(

) : SimpleVM<List<WorkoutExercise>>() {

    private val dummyItem = listOf(
            WorkoutExercise(
                    1L, "Goblet Squad", "work_dummy_1",
                    listOf(
                            WorkoutExerciseIndicators(10, 15F),
                            WorkoutExerciseIndicators(20, 25F),
                            WorkoutExerciseIndicators(10, 15F),
                            WorkoutExerciseIndicators(20, 25F),
                    )
            ),
            WorkoutExercise(
                    2L, "Dumbbell Clean", "work_dummy_2",
                    listOf(
                            WorkoutExerciseIndicators(5, 35.5F),
                            WorkoutExerciseIndicators(100, 4F),
                            WorkoutExerciseIndicators(5, 35.5F),
                            WorkoutExerciseIndicators(100, 4F),
                    )
            ),
    )

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        pushItem(dummyItem)
        return Either.Left(true)
    }
}