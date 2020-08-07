package com.vladislavmyasnikov.feature_diary_impl.di

import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder
import com.vladislavmyasnikov.feature_diary_impl.presentation.DiaryFeatureFlow
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_details.content.DiaryEntryContent
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_details.content.DiaryEntryToolbarContent
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_details.host.DiaryEntryScreenHost
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_list.content.DiaryEntryListContent
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_list.content.DiaryEntryListToolbarContent
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_list.host.DiaryEntryListScreenHost
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_list.viewmodel.DiaryEntryListVM
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_details.viewmodel.DiaryEntryVM
import kotlin.reflect.KClass

object LabelLibraryHolderImpl : LabelLibraryHolder {

    override val labels = listOf<Pair<KClass<*>, String>>(
            // content fragments
            DiaryEntryListContent::class to "DIARY_ENTRY_LIST_CONTENT",
            DiaryEntryListToolbarContent::class to "DIARY_ENTRY_TOOLBAR_CONTENT",
            DiaryEntryContent::class to "DIARY_ENTRY_CONTENT",
            DiaryEntryToolbarContent::class to "DIARY_ENTRY_TOOLBAR_CONTENT",

            // host fragments
            DiaryEntryListScreenHost::class to "DIARY_ENTRY_LIST_SCREEN_HOST",
            DiaryEntryScreenHost::class to "DIARY_ENTRY_DETAILS_SCREEN_HOST",

            // flow fragments
            DiaryFeatureFlow::class to "DIARY_FEATURE_FLOW",

            // dialogs

            // view models
            DiaryEntryListVM::class to "DIARY_ENTRY_LIST_VM",
            DiaryEntryVM::class to "DIARY_ENTRY_VM"
    )
}