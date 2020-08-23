package com.vladislavmyasnikov.exercise_library_impl.domain.usecase.filtered_exercise_list

import com.vladislavmyasnikov.exercise_library_impl.domain.entity.ShortExercise
import io.reactivex.Observable

interface GetFilteredExerciseListUC {

    fun getFilteredExercises(): Observable<List<ShortExercise>>
}