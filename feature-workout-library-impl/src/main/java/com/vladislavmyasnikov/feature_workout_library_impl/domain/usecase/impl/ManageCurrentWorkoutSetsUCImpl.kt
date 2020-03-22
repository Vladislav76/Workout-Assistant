package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.impl

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.*
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.*
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@PerFeature
class ManageCurrentWorkoutSetsUCImpl @Inject constructor(
        private val getWorkoutPlanSetsUC: GetWorkoutPlanSetsUC
) : GetCurrentWorkoutExercisesUC, ChangeWorkoutSetConfigUC, GetWorkoutSetConfigUC, GetWorkoutExerciseUC {

    private var currentSetNumber = -1
    private var currentApproach = -1
    private var currentWorkoutPlanID = -1L
    private var currentWorkoutSets = listOf<WorkoutSet>()
    private lateinit var currentExercisesSubject: PublishSubject<List<WorkoutExercise>>
    private val workoutSetConfigSubject = PublishSubject.create<WorkoutSetConfig>()

    override fun invoke(workoutPlanID: Long, spike: Int): Observable<List<WorkoutExercise>> {
        if (workoutPlanID != currentWorkoutPlanID) {
            currentExercisesSubject = PublishSubject.create<List<WorkoutExercise>>()
            currentWorkoutPlanID = workoutPlanID

            // TODO: what to do with disposable ???
            val disposable = getWorkoutPlanSetsUC(workoutPlanID)
                    .subscribeOn(Schedulers.io())
                    .subscribe({ sets ->
                        currentWorkoutSets = sets
                        setInitialNumberAndApproach()
                    }, { error ->
                        // TODO: send error
                    })
        }
        return currentExercisesSubject
    }

    override fun setNumber(number: Int) {
        if (number != currentSetNumber) {
            currentSetNumber = number
            currentApproach = 0
            pushWorkoutExercises()
            pushWorkoutSetConfig()
        }
    }

    override fun setApproach(approach: Int) {
        if (approach != currentApproach) {
            currentApproach = approach
            pushWorkoutExercises()
            pushWorkoutSetConfig()
        }
    }

    override fun invoke(): Observable<WorkoutSetConfig> = workoutSetConfigSubject

    override fun invoke(exerciseID: Long): WorkoutExercise {
        val exercise = currentWorkoutSets[currentSetNumber].elements.find { exercise -> exercise.first.id == exerciseID }!!
        return WorkoutExercise(exercise.first, exercise.second[currentApproach])
    }

    private fun setInitialNumberAndApproach() {
        currentSetNumber = 0
        currentApproach = 0
        pushWorkoutExercises()
        pushWorkoutSetConfig()
    }

    private fun pushWorkoutExercises() {
        val result = currentWorkoutSets[currentSetNumber].elements.map { exercise ->
            WorkoutExercise(exercise.first, exercise.second[currentApproach])
        }
        currentExercisesSubject.onNext(result)
    }

    private fun pushWorkoutSetConfig() {
        workoutSetConfigSubject.onNext(WorkoutSetConfig(currentSetNumber, currentApproach, currentWorkoutSets.size, getCurrentApproachAmount()))
    }

    private fun getCurrentApproachAmount() = currentWorkoutSets[currentSetNumber].elements[0].second.size
}