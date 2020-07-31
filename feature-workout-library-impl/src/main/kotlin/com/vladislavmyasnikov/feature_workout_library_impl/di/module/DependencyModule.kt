package com.vladislavmyasnikov.feature_workout_library_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.feature_diary_api.DiaryFeatureApi
import com.vladislavmyasnikov.feature_exercise_library_api.ExerciseLibraryFeatureApi
import dagger.Module
import dagger.Provides

@Module
class DependencyModule {

    @Provides @PerFeature
    fun provide1(api: ExerciseLibraryFeatureApi) = api.exerciseLibraryInteractor()

    @Provides @PerFeature
    fun provide2(api: DiaryFeatureApi) = api.diaryInteractor()
}