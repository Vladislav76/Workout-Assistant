package com.vladislavmyasnikov.workout_diary_impl.di.module

import com.vladislavmyasnikov.common.arch.component.FlowLauncher
import com.vladislavmyasnikov.common.di.annotations.PerFlow
import com.vladislavmyasnikov.common.di.modules.LocalNavigationModule
import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder
import com.vladislavmyasnikov.workout_diary_impl.data.repository.WorkoutDiaryRepositoryImpl
import com.vladislavmyasnikov.workout_diary_impl.di.LabelLibraryHolderImpl
import com.vladislavmyasnikov.workout_diary_impl.domain.repository.WorkoutDiaryRepository
import com.vladislavmyasnikov.workout_diary_impl.presentation.DiaryFlow
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [LocalNavigationModule::class, DependencyModule::class])
abstract class FeatureModule {

    @Binds @PerFlow
    abstract fun bind2(impl: WorkoutDiaryRepositoryImpl): WorkoutDiaryRepository

    @Binds
    abstract fun bind3(impl: DiaryFlow.Launcher): FlowLauncher

    companion object {
        @Provides
        @PerFlow
        fun provide1(): LabelLibraryHolder = LabelLibraryHolderImpl
    }
}