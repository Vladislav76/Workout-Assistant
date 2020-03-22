package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.impl

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.ShortWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.repository.WorkoutRepository
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.GetWorkoutPlansUC
import io.reactivex.Observable
import javax.inject.Inject

class GetWorkoutPlansUCImpl @Inject constructor(
        private val workoutRepository: WorkoutRepository
) : GetWorkoutPlansUC {

    override fun invoke(): Observable<List<ShortWorkout>> = workoutRepository.fetchShortWorkoutList()
}