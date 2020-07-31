package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.*
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_list.GetWorkoutSetListUC
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@PerScreen
class WorkoutSetControllerUCImpl @Inject constructor(
        private val getWorkoutSetListUC: GetWorkoutSetListUC
) : RequestWorkoutUC, GetCurrentWorkoutExerciseListUC, GetCurrentWorkoutSetConfigUC, GetWorkoutExerciseUC, ChangeWorkoutSetConfigUC {

    private var currentSetIndex = -1
    private var currentApproachIndex = -1
    private var currentWorkoutPlanID = -1L
    private var currentSets = listOf<WorkoutSet>()

    private var workoutExerciseListSubject = PublishSubject.create<List<WorkoutExercise>>()
    private val workoutSetConfigSubject = PublishSubject.create<WorkoutSetConfig>()
    private val disposables = CompositeDisposable()

    override fun requestWorkoutById(id: Long) {
        getWorkoutSetListUC.getAllSetsByWorkoutId(id)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { sets ->
                            currentSets = sets
                            currentWorkoutPlanID = id
                            initialSetup()
                        },
                        { error -> /* TODO: send error */ }
                )
                .also { disposable -> disposables.add(disposable) }
    }

    override fun getCurrentWorkoutExercises(): Observable<List<WorkoutExercise>> = workoutExerciseListSubject

    override fun getCurrentWorkoutSetConfig(): Observable<WorkoutSetConfig> = workoutSetConfigSubject

    override fun getWorkoutExerciseById(exerciseID: Long): WorkoutExercise {
        val exercise = currentSets[currentSetIndex].elements.find { exercise -> exercise.first.id == exerciseID }!!
        return WorkoutExercise(exercise.first, exercise.second[currentApproachIndex])
    }

    override fun putSetIndex(index: Int) {
        if (currentSetIndex != index) {
            currentSetIndex = index
            currentApproachIndex = 0
            pushCurrentWorkoutExerciseList()
            pushCurrentWorkoutSetConfig()
        }
    }

    override fun putApproachIndex(index: Int) {
        if (currentApproachIndex != index) {
            currentApproachIndex = index
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