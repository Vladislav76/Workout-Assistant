package com.vladislav.workoutassistant.ui.workouts.viewmodels

import android.app.Application

import com.vladislav.workoutassistant.data.DataRepository
import com.vladislav.workoutassistant.data.db.entity.Exercise
import com.vladislav.workoutassistant.data.models.Item
import com.vladislav.workoutassistant.data.models.WorkoutExercise
import com.vladislav.workoutassistant.data.models.WorkoutProgram
import com.vladislav.workoutassistant.data.models.WorkoutSet

import java.util.ArrayList

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {

    private val mDataRepository: DataRepository
    var workoutProgram: WorkoutProgram? = null
        private set
    val muscleGroups: List<Item>
    private val mSetsAndExercises = MediatorLiveData<List<Any>>()

    val setsAndExercisesList: LiveData<List<Any>>
        get() = mSetsAndExercises

    init {
        mDataRepository = DataRepository.getInstance(application)
        muscleGroups = mDataRepository.loadMuscleGroups()
    }

    fun init(workoutId: Int) {
        mSetsAndExercises.addSource(mDataRepository.loadWorkoutProgram(workoutId)) { program ->
            val workoutSets = program.workoutSets
            if (workoutSets != null) {
                val exerciseIds = ArrayList<Int>()
                for (set in workoutSets) {
                    for (workoutExercise in set.workoutExercises) {
                        exerciseIds.add(workoutExercise.matchingInfo.exerciseId)
                    }
                }
                mSetsAndExercises.addSource(mDataRepository.loadExercises(exerciseIds)) { exercises ->
                    //TODO: how to work in background?
                    if (exercises != null) {
                        for (workoutSet in workoutSets) {
                            addExerciseDataToSet(workoutSet.workoutExercises, exercises)
                        }
                        mSetsAndExercises.postValue(workoutProgramToList(program))
                        workoutProgram = program
                    }
                }
            }
        }
    }

    private fun workoutProgramToList(program: WorkoutProgram): List<Any> {
        val list = ArrayList<Any>()
        val sets = program.workoutSets
        for (set in sets) {
            list.add(set)
            list.addAll(set.workoutExercises)
        }
        return list
    }

    private fun addExerciseDataToSet(workoutExercises: List<WorkoutExercise>, exercises: List<Exercise>?) {
        for (workoutExercise in workoutExercises) {
            val keyId = workoutExercise.matchingInfo.exerciseId
            for (exercise in exercises!!) {
                if (exercise.id == keyId) {
                    workoutExercise.exerciseInfo = exercise
                    break
                }
            }
        }
    }
}
