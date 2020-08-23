package com.vladislavmyasnikov.exercise_library_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.LocalNavigationModule
import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder
import com.vladislavmyasnikov.exercise_library_api.ExerciseLibraryInteractor
import com.vladislavmyasnikov.exercise_library_api.ExerciseLibraryLauncher
import com.vladislavmyasnikov.exercise_library_impl.data.repository.ExerciseRepositoryImpl
import com.vladislavmyasnikov.exercise_library_impl.di.LabelLibraryHolderImpl
import com.vladislavmyasnikov.exercise_library_impl.domain.repository.ExerciseRepository
import com.vladislavmyasnikov.exercise_library_impl.presentation.ExerciseLibraryFlow
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class, LocalNavigationModule::class])
abstract class FeatureModule {

    @Binds @PerFeature
    abstract fun bind1(impl: ExerciseLibraryFlow): ExerciseLibraryLauncher

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