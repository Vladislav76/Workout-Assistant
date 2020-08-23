package com.vladislavmyasnikov.exercise_library_impl.domain.usecase.exercise_list

import com.vladislavmyasnikov.exercise_library_impl.domain.entity.ShortExercise
import com.vladislavmyasnikov.exercise_library_impl.domain.repository.ExerciseRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetExerciseListUCImpl @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : GetExerciseListUC {

    override fun invoke(): Observable<List<ShortExercise>> = exerciseRepository.fetchAllExercises()
}