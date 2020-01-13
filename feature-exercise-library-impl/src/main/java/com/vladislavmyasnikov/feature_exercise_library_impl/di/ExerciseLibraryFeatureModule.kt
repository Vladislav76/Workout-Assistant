package com.vladislavmyasnikov.feature_exercise_library_impl.di

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.FactoryModule
import com.vladislavmyasnikov.common.di.modules.LocalNavigationModule
import com.vladislavmyasnikov.feature_exercise_library_impl.data.repo_mapper_impl.ExerciseRepositoryImpl
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ExerciseRepository
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.FlowFragment
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryInteractor
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryLauncher
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [ExerciseLibraryFeatureModule.Bindings::class, LocalNavigationModule::class])
class ExerciseLibraryFeatureModule {

    @Provides
    @PerFeature
    fun provideExerciseLibraryLauncher() = object : ExerciseLibraryLauncher {
        override fun launch(): Fragment {
            return FlowFragment.newInstance()
        }
    }

    // TODO: delete inner class
    @Module(includes = [DatabaseModule::class])
    abstract class Bindings {

        @Binds
        @PerFeature
        abstract fun provideExerciseRepository(impl: ExerciseRepositoryImpl): ExerciseRepository

        @Binds
        @PerFeature
        abstract fun provideExerciseLibraryInteractor(impl: ExerciseRepositoryImpl): ExerciseLibraryInteractor
    }
}