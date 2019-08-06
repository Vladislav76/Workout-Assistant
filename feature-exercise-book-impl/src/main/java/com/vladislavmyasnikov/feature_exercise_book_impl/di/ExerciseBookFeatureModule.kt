package com.vladislavmyasnikov.feature_exercise_book_impl.di

import android.content.Context
import com.vladislavmyasnikov.core_components.di.ContextModule
import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.feature_exercise_book_api.ExerciseBookLauncher
import com.vladislavmyasnikov.feature_exercise_book_impl.api_impl.ExerciseBookLauncherImpl
import com.vladislavmyasnikov.feature_exercise_book_impl.data.repo_mapper_impl.ExerciseRepositoryImpl
import com.vladislavmyasnikov.feature_exercise_book_impl.domain.ExerciseRepository
import com.vladislavmyasnikov.feature_exercise_book_impl.presentation.adapters.ExerciseAdapter
import com.vladislavmyasnikov.feature_exercise_book_impl.presentation.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [ExerciseBookFeatureModule.Bindings::class, ContextModule::class])
class ExerciseBookFeatureModule {

    @PerFeature
    @Provides
    fun provideViewModelFactory(repository: ExerciseRepository): ViewModelFactory = ViewModelFactory(repository)

    @Provides
    @PerFeature
    fun provideExerciseAdapter(context: Context): ExerciseAdapter = ExerciseAdapter(context)

    @Module(includes = [DatabaseModule::class])
    abstract class Bindings {

        @PerFeature
        @Binds
        abstract fun provideExerciseBookLauncher(impl: ExerciseBookLauncherImpl): ExerciseBookLauncher

        @PerFeature
        @Binds
        abstract fun provideExerciseRepository(impl: ExerciseRepositoryImpl): ExerciseRepository
    }
}