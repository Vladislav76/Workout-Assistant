package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.*
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_list.GetWorkoutSetListUC
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@PerScreen
class WorkoutSetControllerUCImpl @Inject constructor(
        private val getWorkoutSetListUC: GetWorkoutSetListUC
) : RequestWorkoutUC, GetWorkoutExerciseListUC, GetWorkoutSetConfigUC, GetSelectedWorkoutExerciseUC, ChangeWorkoutSetConfigUC {

    private var currentSetIndex = -1
    private var currentApproachIndex = -1
    private var currentWorkoutPlanID = -1L
    private var currentSets = listOf<WorkoutSet>()

    private var workoutExerciseListSubject = PublishSubject.create<List<WorkoutExercise>>()
    private val workoutSetConfigSubject = PublishSubject.create<WorkoutSetConfig>()
    private val disposables = CompositeDisposable()

    override fun invoke(workoutPlanID: Long) {
        getWorkoutSetListUC(workoutPlanID)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { sets ->
                            currentSets = sets
                            currentWorkoutPlanID = workoutPlanID
                            initialSetup()
                        },
                        { error -> /* TODO: send error */ }
                )
                .also { disposable -> disposables.add(disposable) }
    }

    override fun invoke(spike: Int): Observable<List<WorkoutExercise>> = workoutExerciseListSubject

    override fun invoke(): Observable<WorkoutSetConfig> = workoutSetConfigSubject

    override fun invoke(exerciseID: Long, spike: Int): WorkoutExercise {
        val exercise = currentSets[currentSetIndex].elements.find { exercise -> exercise.first.id == exerciseID }!!
        return WorkoutExercise(exercise.first, exercise.second[currentApproachIndex])
    }

    override fun putSetIndex(setIndex: Int) {
        if (setIndex != currentSetIndex) {
            currentSetIndex = setIndex
            currentApproachIndex = 0
            pushCurrentWorkoutExerciseList()
            pushCurrentWorkoutSetConfig()
        }
    }

    override fun putApproachIndex(approachIndex: Int) {
        if (approachIndex != currentApproachIndex) {
            currentApproachIndex = approachIndex
            pushCurrentWorkoutExerciseList()
            pushCurrentWorkoutSetConfig()
        }
    }

    private fun initialSetup() {
        currentSetIndex = 0
        currentApproachIndex = 0
        pushCurrentWorkoutExerciseList()
        pushCurrentWorkoutSetConfig()
    }

    private fun pushCurrentWorkoutExerciseList() {
        val exercises = currentSets[currentSetIndex].elements.map { exercise -> WorkoutExercise(exercise.first, exercise.second[currentApproachIndex]) }
        workoutExerciseListSubject.onNext(exercises)
    }

    private fun pushCurrentWorkoutSetConfig() {
        val exerciseAmount = currentSets[currentSetIndex].elements.size
        val approachAmount = currentSets[currentSetIndex].elements[0].second.size
        workoutSetConfigSubject.onNext(WorkoutSetConfig(currentSetIndex, currentApproachIndex, currentSets.size, exerciseAmount, approachAmount))
    }
}