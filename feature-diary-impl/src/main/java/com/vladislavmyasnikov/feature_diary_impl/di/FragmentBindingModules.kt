package com.vladislavmyasnikov.feature_diary_impl.di

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.di.annotations.FragmentKey
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry.content.DiaryEntryContent
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry.content.DiaryEntryToolbarContent
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry.host.DiaryEntryHost
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry_list.content.DiaryEntryListContent
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry_list.content.DiaryEntryListToolbarContent
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry_list.host.DiaryEntryListHost
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContentFragmentBindingModule {

    @Binds @IntoMap @PerScreen @FragmentKey(DiaryEntryListContent::class)
    abstract fun bind1(impl: DiaryEntryListContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(DiaryEntryListToolbarContent::class)
    abstract fun bind2(impl: DiaryEntryListToolbarContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(DiaryEntryContent::class)
    abstract fun bind3(impl: DiaryEntryContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(DiaryEntryToolbarContent::class)
    abstract fun bind4(impl: DiaryEntryToolbarContent): Fragment
}

@Module
abstract class HostFragmentBindingModule {

    @Binds @IntoMap @PerFeature @FragmentKey(DiaryEntryListHost::class)
    abstract fun bind1(impl: DiaryEntryListHost): Fragment

    @Binds @IntoMap @PerFeature @FragmentKey(DiaryEntryHost::class)
    abstract fun bind2(impl: DiaryEntryHost): Fragment
}