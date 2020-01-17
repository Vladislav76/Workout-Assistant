package com.vladislavmyasnikov.feature_exercise_library_impl.di

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.di.annotations.FragmentKey
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.content.ExerciseFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.content.ExerciseListFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.content.ExerciseListToolbarFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.host.ExerciseDetailsFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.host.ExerciseLibraryFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBindingModule {

    // content fragments
    @Binds @IntoMap @PerFeature @FragmentKey(ExerciseListFragment::class)
    abstract fun bindExerciseListFragment(fragment: ExerciseListFragment): Fragment

    @Binds @IntoMap @PerFeature @FragmentKey(ExerciseListToolbarFragment::class)
    abstract fun bindExerciseListToolbarFragment(fragment: ExerciseListToolbarFragment): Fragment

    @Binds @IntoMap @PerFeature @FragmentKey(ExerciseFragment::class)
    abstract fun bindExerciseFragment(fragment: ExerciseFragment): Fragment

    // host fragments
    @Binds @IntoMap @PerFeature @FragmentKey(ExerciseLibraryFragment::class)
    abstract fun bindExerciseLibraryFragment(fragment: ExerciseLibraryFragment): Fragment

    @Binds @IntoMap @PerFeature @FragmentKey(ExerciseDetailsFragment::class)
    abstract fun bindExerciseDetailsFragment(fragment: ExerciseDetailsFragment): Fragment
}