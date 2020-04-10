package com.vladislavmyasnikov.feature_exercise_library_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.LocalNavigationModule
import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder
import com.vladislavmyasnikov.feature_exercise_library_impl.data.repo_mapper_impl.ExerciseRepositoryImpl
import com.vladislavmyasnikov.feature_exercise_library_impl.di.LabelLibraryHolderImpl
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.repository.ExerciseRepository
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.ExerciseLibraryFeatureFlow
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryInteractor
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryLauncher
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class, LocalNavigationModule::class])
abstract class FeatureModule {

    @Binds @PerFeature
    abstract fun bind1(impl: ExerciseLibraryFeatureFlow): ExerciseLibraryLauncher

    @Binds @PerFeature
    abstract fun bind2(impl: ExerciseRepositoryImpl): ExerciseRepository

    @Binds @PerFeature
    abstract fun bind3(impl: ExerciseRepositoryImpl): ExerciseLibraryInteractor

    companion object {
        @Provides
        @PerFeature
        fun provide1(): LabelLibraryHolder = LabelLibraryHolderImpl
    }
}