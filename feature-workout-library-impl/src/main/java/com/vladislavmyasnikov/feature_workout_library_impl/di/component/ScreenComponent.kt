package com.vladislavmyasnikov.feature_workout_library_impl.di.component

import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.di.module.AdapterModule
import com.vladislavmyasnikov.feature_workout_library_impl.di.module.ContentFragmentBindingModule
import com.vladislavmyasnikov.feature_workout_library_impl.di.module.VMBindingModule
import dagger.Subcomponent

@Subcomponent(modules = [AdapterModule::class, ContentFragmentBindingModule::class, VMBindingModule::class])
@PerScreen
abstract class ScreenComponent {

    abstract val fragmentFactory: FragmentFactory
}