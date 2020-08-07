package com.vladislavmyasnikov.feature_diary_impl.di.module

import androidx.lifecycle.ViewModel
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.common.di.annotations.ViewModelKey
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_list.viewmodel.DiaryEntryListVM
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_details.viewmodel.DiaryEntryVM
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