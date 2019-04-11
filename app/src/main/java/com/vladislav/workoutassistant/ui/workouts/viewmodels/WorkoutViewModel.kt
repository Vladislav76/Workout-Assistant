package com.vladislav.workoutassistant.ui.workouts.viewmodels

import android.app.Application
import androidx.lifecycle.*

import com.vladislav.workoutassistant.data.DataRepository
import com.vladislav.workoutassistant.data.db.entity.Exercise
import com.vladislav.workoutassistant.data.models.*

import java.util.ArrayList

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {

    private val mDataRepository: DataRepository = DataRepository.getInstance(application)
    private val mWorkoutInfo = MediatorLiveData<WorkoutInfo>()

    val muscleGroups: List<Item> = mDataRepository.loadMuscleGroups()
    val workoutInfo: LiveData<WorkoutInfo>
        get() = mWorkoutInfo

    var workoutProgram: WorkoutProgram? = null
        private set

    private val mSetsAndExercises = MediatorLiveData<List<Any>>()

    val setsAndExercisesList: LiveData<List<Any>>
        get() = mSetsAndExercises

    fun init(workoutId: Int) {
        mSetsAndExercises.addSource(mDataRepository.loadWorkoutProgram(workoutId)) { program ->
            val workoutSets = program.workoutSets
            if (workoutSets != null) {
                val exerciseIds = ArrayList<Int>()
                for (set in workoutSets) {
                    for (workoutExercise in set.workoutExercises!!) {
                        exerciseIds.add(workoutExercise.matchingInfo!!.exerciseId)
                    }
                }
                mSetsAndExercises.addSource(mDataRepository.loadExercises(exerciseIds)) { exercises ->
                    //TODO: how to work in background?
                    if (exercises != null) {
                        for (workoutSet in workoutSets) {
                            addExerciseDataToSet(workoutSet.workoutExercises!!, exercises)
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
        for (set in sets!!) {
            list.add(set)
            list.addAll(set.workoutExercises!!)
        }
        return list
    }

    private fun addExerciseDataToSet(workoutExercises: List<WorkoutExercise>, exercises: List<Exercise>?) {
        for (workoutExercise in workoutExercises) {
            val keyId = workoutExercise.matchingInfo!!.exerciseId
            for (exercise in exercises!!) {
                if (exercise.id == keyId) {
                    workoutExercise.exerciseInfo = exercise
                    break
                }
            }
        }
    }

    fun loadWorkoutInfoById(id: Int) {

        fun extractExercisesIds(sets: List<SetInfo>): List<Int> {
            val ids = ArrayList<Int>()
            for (set in sets) {
                for (exercise in set.exercises) {
                    ids.add(exercise.id)
                }
            }
            return ids
        }

        fun addExerciseContent(sets: List<SetInfo>, content: List<ExerciseContent>) {
            for (set in sets) {
                for (exercise in set.exercises) {
                    val exerciseContent = content.find { it.id == exercise.id }
                    exercise.name = exerciseContent?.name ?: ""
                    exercise.muscleGroupId = exerciseContent?.muscleGroupId ?: 0
                }
            }
        }

        mWorkoutInfo.addSource(mDataRepository.loadWorkoutInfo(id)) { info ->
            val ids: List<Int> = extractExercisesIds(info.sets)
            mWorkoutInfo.addSource(mDataRepository.loadExercisesById(ids)) { content ->
                addExerciseContent(info.sets, content)
                mWorkoutInfo.postValue(info)
            }
        }
    }
}
