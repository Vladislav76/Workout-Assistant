package com.vladislavmyasnikov.feature_diary_impl.presentation.legacy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryEntryRepository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: DiaryEntryRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return when {
                modelClass.isAssignableFrom(DiaryEntryListViewModel::class.java) -> DiaryEntryListViewModel(repository) as T
                modelClass.isAssignableFrom(DiaryEntryViewModel::class.java) -> DiaryEntryViewModel(repository) as T
                else -> throw IllegalArgumentException("ViewModel not found")
            }
    }
}