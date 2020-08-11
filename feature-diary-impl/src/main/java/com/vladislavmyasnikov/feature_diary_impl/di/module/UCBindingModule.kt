package com.vladislavmyasnikov.feature_diary_impl.di.module

import com.vladislavmyasnikov.feature_diary_impl.domain.usecase.diary_entry_details.GetDiaryEntryUC
import com.vladislavmyasnikov.feature_diary_impl.domain.usecase.diary_entry_details.GetDiaryEntryUCImpl
import com.vladislavmyasnikov.feature_diary_impl.domain.usecase.diary_entry_list.GetDiaryEntryListUC
import com.vladislavmyasnikov.feature_diary_impl.domain.usecase.diary_entry_list.GetDiaryEntryListUCImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UCBindingModule {

    @Binds
    abstract fun bind1(impl: GetDiaryEntryListUCImpl): GetDiaryEntryListUC

    @Binds
    abstract fun bind2(impl: GetDiaryEntryUCImpl): GetDiaryEntryUC
}