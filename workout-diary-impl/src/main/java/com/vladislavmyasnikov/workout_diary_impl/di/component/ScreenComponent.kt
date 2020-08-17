package com.vladislavmyasnikov.workout_diary_impl.di.component

import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.workout_diary_impl.di.module.AdapterModule
import com.vladislavmyasnikov.workout_diary_impl.di.module.ContentFragmentBindingModule
import com.vladislavmyasnikov.workout_diary_impl.di.module.UCBindingModule
import com.vladislavmyasnikov.workout_diary_impl.di.module.VMBindingModule
import dagger.Subcomponent

@Subcomponent(modules = [AdapterModule::class, ContentFragmentBindingModule::class, VMBindingModule::class, UCBindingModule::class])
@PerScreen
abstract class ScreenComponent {

    abstract val fragmentFactory: FragmentFactory
}