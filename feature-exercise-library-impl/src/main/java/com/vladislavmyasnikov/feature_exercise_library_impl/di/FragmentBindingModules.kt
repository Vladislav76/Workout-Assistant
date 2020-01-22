package com.vladislavmyasnikov.feature_exercise_library_impl.di

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.di.annotations.FragmentKey
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.content.ExerciseFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.content.ExerciseListFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.content.ExerciseListToolbarFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.host.ExerciseDetailsFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.host.ExerciseLibraryFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContentFragmentBindingModule {

    @Binds @IntoMap @PerScreen @FragmentKey(ExerciseListFragment::class)
    abstract fun bindExerciseListFragment(impl: ExerciseListFragment): Fragment

    @Binds @IntoMap @PerScreen  @FragmentKey(ExerciseListToolbarFragment::class)
    abstract fun bindExerciseListToolbarFragment(impl: ExerciseListToolbarFragment): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(ExerciseFragment::class)
    abstract fun bindExerciseFragment(impl: ExerciseFragment): Fragment
}

@Module
abstract class HostFragmentBindingModule {

    @Binds @IntoMap @PerFeature @FragmentKey(ExerciseLibraryFragment::class)
    abstract fun bindExerciseLibraryFragment(impl: ExerciseLibraryFragment): Fragment

    @Binds @IntoMap @PerFeature @FragmentKey(ExerciseDetailsFragment::class)
    abstract fun bindExerciseDetailsFragment(impl: ExerciseDetailsFragment): Fragment
}