package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_set_list

import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutSet
import io.reactivex.Single

interface GetWorkoutSetListUC {

    fun getAllSetsByWorkoutId(id: Long): Single<List<WorkoutSet>>
}