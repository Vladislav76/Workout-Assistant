package com.vladislavmyasnikov.app.di

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.app.presentation.Controller
import com.vladislavmyasnikov.common.di.annotations.FragmentKey
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.WorkoutLibraryFlow
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.terrakok.cicerone.Cicerone

@Module
class MainActivityModule {

    private val mainCicerone = Cicerone.create()

    @Provides
    fun provideController() = Controller

    @Provides
    fun provideMainRouter() = mainCicerone.router

    @Provides
    fun provideMainNavigatorHolder() = mainCicerone.navigatorHolder
}

@Module
abstract class FlowFragmentBindingModule {

    @Binds @IntoMap @FragmentKey(WorkoutLibraryFlow::class)
    abstract fun bind1(fragment: WorkoutLibraryFlow): Fragment
}