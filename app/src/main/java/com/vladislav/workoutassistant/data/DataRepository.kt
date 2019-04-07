package com.vladislav.workoutassistant.data

import android.app.Application

import com.vladislav.workoutassistant.R
import com.vladislav.workoutassistant.data.db.LocalDatabase
import com.vladislav.workoutassistant.data.db.entity.DiaryEntry
import com.vladislav.workoutassistant.data.db.entity.Exercise
import com.vladislav.workoutassistant.data.db.entity.Workout
import com.vladislav.workoutassistant.data.models.WorkoutProgram

import java.util.ArrayList
import java.util.Date
import java.util.concurrent.Executor
import java.util.concurrent.Executors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.vladislav.workoutassistant.data.models.Item
import com.vladislav.workoutassistant.data.models.Nameable

class DataRepository private constructor(application: Application) {

    private val mExecutor: Executor
    private val mDatabase: LocalDatabase = LocalDatabase.getInstance(application)
    private val mObservableEntries: MediatorLiveData<List<DiaryEntry>> = MediatorLiveData()
    private val mCategories: MutableList<Item>
    private val mIntensityLevels: MutableList<Item>
    private val mMuscleGroups: MutableList<Item>

    val newEntry: DiaryEntry
        get() = DiaryEntry()

    init {
        mObservableEntries.addSource(mDatabase.diaryDao().loadAllEntries()) { diaryEntryEntities ->
            if (diaryEntryEntities != null) {
                mObservableEntries.postValue(diaryEntryEntities)
            }
        }

        mCategories = ArrayList()
        var names = application.resources.getStringArray(R.array.categories)
        for (i in names.indices) {
            mCategories.add(Item(i, names[i]))
        }

        mIntensityLevels = ArrayList()
        names = application.resources.getStringArray(R.array.intensity_levels)
        for (i in names.indices) {
            mIntensityLevels.add(Item(i, names[i]))
        }

        mMuscleGroups = ArrayList()
        names = application.resources.getStringArray(R.array.muscle_groups)
        for (i in names.indices) {
            mMuscleGroups.add(Item(i, names[i]))
        }

        mExecutor = Executors.newSingleThreadExecutor()
    }

    fun loadCategories(): List<Item> {
        return mCategories
    }

    fun loadIntensityLevels(): List<Item> {
        return mIntensityLevels
    }

    fun loadMuscleGroups(): List<Item> {
        return mMuscleGroups
    }

    fun loadWorkouts(categoryId: Int): LiveData<List<Workout>> {
        return mDatabase.workoutDao().loadWorkouts(categoryId)
    }

    fun loadExercise(exerciseId: Int): LiveData<Exercise> {
        return mDatabase.exerciseDao().loadExercise(exerciseId)
    }

    fun loadExercises(exerciseIds: List<Int>): LiveData<List<Exercise>> {
        return mDatabase.exerciseDao().loadExercises(exerciseIds)
    }

    fun loadExercises(muscleGroupId: Int): LiveData<List<Exercise>> {
        return mDatabase.exerciseDao().loadExercises(muscleGroupId)
    }

    fun loadWorkoutProgram(workoutId: Int): LiveData<WorkoutProgram> {
        return mDatabase.workoutDao().loadWorkoutProgram(workoutId)
    }

    fun loadDiaryEntries(): LiveData<List<DiaryEntry>> {
        return mObservableEntries
    }

    fun loadDiaryEntry(diaryEntryId: Int): LiveData<DiaryEntry> {
        return mDatabase.diaryDao().loadEntry(diaryEntryId)
    }

    fun insertNewEntry(diaryEntry: DiaryEntry) {
        mExecutor.execute { mDatabase.diaryDao().insertEntry(diaryEntry) }
    }

    fun updateEntry(diaryEntry: DiaryEntry) {
        mExecutor.execute { mDatabase.diaryDao().updateEntry(diaryEntry) }
    }

    fun deleteEntriesById(ids: List<Int>) {
        mExecutor.execute { mDatabase.diaryDao().deleteEntriesById(ids) }
    }

    companion object {

        private var sInstance: DataRepository? = null

        fun getInstance(application: Application): DataRepository {
            return sInstance ?: synchronized(this) {
                sInstance ?: DataRepository(application).also { sInstance = it }
            }
        }
    }
}