package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.ExerciseApproachData
import io.reactivex.Observable

interface GetCurrentExerciseApproachDataUC {

    operator fun invoke(spike: Int = 0, spike2: Int = 0): Observable<ExerciseApproachData>
}