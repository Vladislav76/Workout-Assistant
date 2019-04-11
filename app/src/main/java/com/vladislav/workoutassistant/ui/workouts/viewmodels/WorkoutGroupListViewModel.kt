package com.vladislav.workoutassistant.ui.workouts.viewmodels

import android.app.Application

import com.vladislav.workoutassistant.data.DataRepository
import com.vladislav.workoutassistant.data.db.entity.Workout
import com.vladislav.workoutassistant.data.models.Item
import com.vladislav.workoutassistant.data.models.WorkoutGroup

import java.util.ArrayList

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

class WorkoutGroupListViewModel(application: Application) : AndroidViewModel(application) {

    private val mDataRepository: DataRepository
    private val mIntensityLevels: List<Item>
    val categories: List<Item>
    private val mWorkoutGroups = MediatorLiveData<List<WorkoutGroup>>()

    val workoutGroups: LiveData<List<WorkoutGroup>>
        get() = mWorkoutGroups

    init {
        mDataRepository = DataRepository.getInstance(application)
        mIntensityLevels = mDataRepository.loadIntensityLevels()
        categories = mDataRepository.loadCategories()
    }

    fun init(categoryId: Int) {
        mWorkoutGroups.addSource(mDataRepository.loadWorkouts(categoryId)) { workouts -> mWorkoutGroups.postValue(mergeIntoGroups(workouts)) }
    }

    private fun mergeIntoGroups(workouts: List<Workout>?): List<WorkoutGroup> {
        val groups = ArrayList<WorkoutGroup>()
        if (workouts != null && workouts.size > 0) {
            var startPosition = 0
            var currentGroupId = workouts[0].intensityLevelId  //TODO: don't chain to this method, use groupId
            var group = WorkoutGroup(mIntensityLevels[currentGroupId])

            for (i in 1 until workouts.size) {
                val (_, _, _, intensityLevelId) = workouts[i]
                if (intensityLevelId != currentGroupId) {
                    group.workouts = workouts.subList(startPosition, i)
                    groups.add(group)
                    startPosition = i
                    currentGroupId = workouts[i].intensityLevelId
                    group = WorkoutGroup(mIntensityLevels[currentGroupId])
                }
            }

            group.workouts = workouts.subList(startPosition, workouts.size)
            groups.add(group)
        }
        return groups
    }
}
