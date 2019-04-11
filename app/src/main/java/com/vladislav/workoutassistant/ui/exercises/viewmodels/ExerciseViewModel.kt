package com.vladislav.workoutassistant.ui.exercises.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.vladislav.workoutassistant.data.DataRepository
import com.vladislav.workoutassistant.data.db.entity.Exercise

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private val mDataRepository: DataRepository = DataRepository.getInstance(application)
    private val mExerciseId = MutableLiveData<Int>()

    val exercise: LiveData<Exercise> = Transformations.switchMap(mExerciseId) {
        id -> mDataRepository.loadExercise(id)
    }

    fun loadExerciseById(id: Int) {
        mExerciseId.value = id
    }
}
