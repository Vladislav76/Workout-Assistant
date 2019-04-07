package com.vladislav.workoutassistant.ui.exercises.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.vladislav.workoutassistant.data.DataRepository
import com.vladislav.workoutassistant.data.db.entity.Exercise

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private val mDataRepository: DataRepository = DataRepository.getInstance(application)
    private val mExercise = MediatorLiveData<Exercise>()

    val exercise: LiveData<Exercise>
        get() = mExercise

    fun init(exerciseId: Int) {
        mExercise.addSource(mDataRepository.loadExercise(exerciseId)) { exercise -> mExercise.postValue(exercise) }
    }
}
