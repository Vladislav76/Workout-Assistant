package com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryRepository
import javax.inject.Inject

class DiaryEntryListViewModelFactory @Inject constructor(private val repository: DiaryRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DiaryEntryListViewModel::class.java)) {
            DiaryEntryListViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}