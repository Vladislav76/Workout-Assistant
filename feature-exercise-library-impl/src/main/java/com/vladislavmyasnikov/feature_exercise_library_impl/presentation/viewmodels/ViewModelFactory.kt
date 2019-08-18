package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ExerciseRepository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: ExerciseRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ExerciseListViewModel::class.java) -> ExerciseListViewModel(repository) as T
            modelClass.isAssignableFrom(ExerciseViewModel::class.java) -> ExerciseViewModel(repository) as T
            else -> throw IllegalArgumentException("ViewModel not found")
        }
    }
}