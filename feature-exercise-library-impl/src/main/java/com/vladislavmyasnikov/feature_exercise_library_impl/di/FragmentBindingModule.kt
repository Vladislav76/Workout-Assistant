package com.vladislavmyasnikov.feature_exercise_library_impl.di

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.di.annotations.FragmentKey
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.view.content.ExerciseListFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.view.content.ExerciseListToolbarFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBindingModule {

    @Binds @IntoMap @PerFeature @FragmentKey(ExerciseListFragment::class)
    abstract fun bindExerciseListFragment(fragment: ExerciseListFragment): Fragment

    @Binds @IntoMap @PerFeature @FragmentKey(ExerciseListToolbarFragment::class)
    abstract fun bindExerciseListToolbarFragment(fragment: ExerciseListToolbarFragment): Fragment
}