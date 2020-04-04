package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.impl

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.*
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.*
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@PerFeature
class ManageCurrentWorkoutSetsUCImpl @Inject constructor(
        private val getWorkoutPlanSetsUC: GetWorkoutPlanSetsUC
) : RequestWorkoutPlanInfoUC, GetCurrentWorkoutExercisesUC, GetWorkoutSetConfigUC, GetWorkoutExerciseUC, ChangeWorkoutSetConfigUC {

    private var currentSetNumber = -1
    private var currentApproach = -1
    private var currentWorkoutPlanID = -1L
    private var currentSets = listOf<WorkoutSet>()
    private var currentExercisesSubject = PublishSubject.create<List<WorkoutExercise>>()
    private val workoutSetConfigSubject = PublishSubject.create<WorkoutSetConfig>()

    override fun invoke(workoutPlanID: Long) {
        // TODO: what to do with disposable ???
        // TODO: what if workoutPlanID == currentWorkoutPlanID
        val disposable = getWorkoutPlanSetsUC(workoutPlanID)
                .subscribeOn(Schedulers.io())
                .subscribe({ sets ->
                    currentSets = sets
                    currentWorkoutPlanID = workoutPlanID
                    initialSetup()
                }, { error ->
                    // TODO: send error
                })
    }

    override fun invoke(spike: Int): Observable<List<WorkoutExercise>> = currentExercisesSubject

    override fun invoke(): Observable<WorkoutSetConfig> = workoutSetConfigSubject

    override fun invoke(exerciseID: Long, spike: Int): WorkoutExercise {
        val exercise = currentSets[currentSetNumber].elements.find { exercise -> exercise.first.id == exerciseID }!!
        return WorkoutExercise(exercise.first, exercise.second[currentApproach])
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

    private fun initialSetup() {
        currentSetNumber = 0
        currentApproach = 0
        pushWorkoutExercises()
        pushWorkoutSetConfig()
    }

    private fun pushWorkoutExercises() {
        val exercises = currentSets[currentSetNumber].elements.map { exercise -> WorkoutExercise(exercise.first, exercise.second[currentApproach]) }
        currentExercisesSubject.onNext(exercises)
    }

    private fun pushWorkoutSetConfig() {
        val exerciseAmount = currentSets[currentSetNumber].elements.size
        val approachAmount = currentSets[currentSetNumber].elements[0].second.size
        workoutSetConfigSubject.onNext(WorkoutSetConfig(currentSetNumber, currentApproach, currentSets.size, exerciseAmount, approachAmount))
    }
}