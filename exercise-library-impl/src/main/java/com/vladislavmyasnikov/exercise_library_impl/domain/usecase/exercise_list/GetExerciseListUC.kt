package com.vladislavmyasnikov.exercise_library_impl.domain.usecase.exercise_list

import com.vladislavmyasnikov.exercise_library_impl.domain.entity.ShortExercise
import io.reactivex.Observable

interface GetExerciseListUC {

    fun invoke(): Observable<List<ShortExercise>>
}