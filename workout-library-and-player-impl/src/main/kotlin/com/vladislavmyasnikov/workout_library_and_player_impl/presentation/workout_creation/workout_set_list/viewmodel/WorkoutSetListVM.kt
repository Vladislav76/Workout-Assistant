package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_set_list.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutSet
import io.reactivex.Completable
import javax.inject.Inject

class WorkoutSetListVM @Inject constructor(

): SimpleVM<List<WorkoutSet>>() {

    private val dummyItem = listOf(
            WorkoutSet(0L, listOf("Goblet Squad", "Dumbbell Clean"), 2)
    )

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        pushItem(dummyItem)
        return Either.Left(true)
    }
}