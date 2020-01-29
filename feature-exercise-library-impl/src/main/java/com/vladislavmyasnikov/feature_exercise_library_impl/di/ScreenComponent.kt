package com.vladislavmyasnikov.feature_exercise_library_impl.di

import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import dagger.Subcomponent

@Subcomponent(modules = [AdapterModule::class, ContentFragmentBindingModule::class, VMBindingModule::class])
@PerScreen
abstract class ScreenComponent {

    abstract val fragmentFactory: FragmentFactory
}