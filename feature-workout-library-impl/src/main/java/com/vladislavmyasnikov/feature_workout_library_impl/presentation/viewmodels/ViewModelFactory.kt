package com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutRepository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: WorkoutRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(WorkoutListViewModel::class.java) -> WorkoutListViewModel(repository) as T
            else -> throw IllegalArgumentException("ViewModel not found")
        }
    }
}