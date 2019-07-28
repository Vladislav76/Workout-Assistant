package com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.core_components.components.GeneralViewModel
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryRepository
import javax.inject.Inject

class DiaryViewModelFactory @Inject constructor(private val repository: DiaryRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return when {
                modelClass.isAssignableFrom(DiaryEntryListViewModel::class.java) -> DiaryEntryListViewModel(repository) as T
                modelClass.isAssignableFrom(DiaryEntryViewModel::class.java) -> DiaryEntryViewModel(repository) as T
                else -> throw IllegalArgumentException("ViewModel not found")
            }
    }
}