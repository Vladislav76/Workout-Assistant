package com.vladislav.workoutassistant.ui.exercises.viewmodels

import android.app.Application
import android.util.SparseIntArray
import androidx.lifecycle.*
import com.vladislav.workoutassistant.data.DataRepository
import com.vladislav.workoutassistant.data.db.entity.Exercise
import com.vladislav.workoutassistant.data.models.Item

class ExerciseListViewModel(application: Application) : AndroidViewModel(application) {

    private val mDataRepository: DataRepository = DataRepository.getInstance(application)
    private val mSelectedExercises: SparseIntArray = SparseIntArray()
    private val mMuscleGroupId = MutableLiveData<Int>()

    val exercises: LiveData<List<Exercise>> = Transformations.switchMap(mMuscleGroupId) {
        id -> mDataRepository.loadExercises(id)
    }

    fun loadExercisesByMuscleGroupId(id: Int) {
        mMuscleGroupId.value = id
    }

    fun getMuscleGroups(): List<Item> = mDataRepository.loadMuscleGroups()

    fun getSelectedExercises(): SparseIntArray = mSelectedExercises

    fun selectExercise(exerciseId: Int, exerciseCount: Int) {
        mSelectedExercises.put(exerciseId, exerciseCount)
    }

    fun unselectExercise(exerciseId: Int) {
        mSelectedExercises.delete(exerciseId)
    }

    fun getExerciseAmount(exerciseId: Int, defaultValue: Int): Int = mSelectedExercises.get(exerciseId, defaultValue)
}
