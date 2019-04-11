package com.vladislav.workoutassistant.ui.workouts.viewmodels

import android.app.Application
import androidx.lifecycle.*

import com.vladislav.workoutassistant.data.DataRepository
import com.vladislav.workoutassistant.data.db.entity.Workout
import com.vladislav.workoutassistant.data.models.Item
import com.vladislav.workoutassistant.data.models.WorkoutGroup

import java.util.ArrayList

class WorkoutGroupListViewModel(application: Application) : AndroidViewModel(application) {

    private val mDataRepository: DataRepository = DataRepository.getInstance(application)
    private val mIntensityLevels: List<Item> = mDataRepository.loadIntensityLevels()
//    private val mCategoryId = MutableLiveData<Int>()

    val categories: List<Item> = mDataRepository.loadCategories()
//    val groups: LiveData<List<WorkoutGroup>> = Transformations.switchMap(mCategoryId) {
//        id -> Transformations.map(mDataRepository.loadWorkouts(id)
//    }

    private val mWorkoutGroups = MediatorLiveData<List<WorkoutGroup>>()

    val workoutGroups: LiveData<List<WorkoutGroup>>
        get() = mWorkoutGroups

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
