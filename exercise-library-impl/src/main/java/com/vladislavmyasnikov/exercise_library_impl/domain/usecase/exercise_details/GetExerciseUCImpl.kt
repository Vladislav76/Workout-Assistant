package com.vladislavmyasnikov.exercise_library_impl.domain.usecase.exercise_details

import com.vladislavmyasnikov.exercise_library_impl.domain.entity.Exercise
import com.vladislavmyasnikov.exercise_library_impl.domain.repository.ExerciseRepository
import io.reactivex.Single
import javax.inject.Inject

class GetExerciseUCImpl @Inject constructor(
        private val exerciseRepository: ExerciseRepository
) : GetExerciseUC {

    override fun getExerciseById(id: Long): Single<Exercise> = exerciseRepository.fetchExerciseById(id)
}