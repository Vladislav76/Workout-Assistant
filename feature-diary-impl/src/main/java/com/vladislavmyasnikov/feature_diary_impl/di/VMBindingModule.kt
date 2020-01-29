package com.vladislavmyasnikov.feature_diary_impl.di

import androidx.lifecycle.ViewModel
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.common.di.annotations.ViewModelKey
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.DiaryEntryListVM
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.DiaryEntryVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VMBindingModule {

    @Binds @IntoMap @PerScreen @ViewModelKey(DiaryEntryListVM::class)
    abstract fun bind1(impl: DiaryEntryListVM): ViewModel

    @Binds @IntoMap @PerScreen @ViewModelKey(DiaryEntryVM::class)
    abstract fun bind2(impl: DiaryEntryVM): ViewModel
}