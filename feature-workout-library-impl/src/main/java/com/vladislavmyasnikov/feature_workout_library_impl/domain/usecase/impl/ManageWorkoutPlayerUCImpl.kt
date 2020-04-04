package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.impl

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.*
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.*
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@PerScreen
class ManageWorkoutPlayerUCImpl @Inject constructor(
        private val requestWorkoutPlanInfoUC: RequestWorkoutPlanInfoUC,
        private val getCurrentWorkoutExercisesUC: GetCurrentWorkoutExercisesUC,
        private val changeWorkoutSetConfigUC: ChangeWorkoutSetConfigUC,
        private val getWorkoutSetConfigUC: GetWorkoutSetConfigUC
) : GetWorkoutExerciseConfigUC, GetCurrentWorkoutExerciseUC, GetCurrentExerciseApproachDataUC, SetCurrentExerciseApproachDataUC, ManageExecutingWorkoutUC {

    private var currentExerciseIndex = -1
    private lateinit var currentSetConfig: WorkoutSetConfig
    private var currentExercises = listOf<WorkoutExercise>()
    private lateinit var workoutData: MutableList<WorkoutSetData>

    private val workoutExerciseSubject = PublishSubject.create<WorkoutExercise>()
    private val workoutExerciseConfigSubject = PublishSubject.create<WorkoutExerciseConfig>()
    private val exerciseApproachDataSubject = PublishSubject.create<ExerciseApproachData>()
    private val disposables = CompositeDisposable()

    override fun invoke(): Observable<WorkoutExerciseConfig> = workoutExerciseConfigSubject

    override fun invoke(spike: Int): Observable<WorkoutExercise> = workoutExerciseSubject

    override fun invoke(spike: Int, spike2: Int): Observable<ExerciseApproachData> = exerciseApproachDataSubject

    override fun setRepetitions(reps: Int) {
        val data = workoutData[currentSetConfig.setIndex].getExerciseApproachData(currentExerciseIndex, currentSetConfig.approachIndex)
        data.reps = reps
        pushCurrentExerciseApproachData()
    }

    override fun setWeight(weight: Float) {
        val data = workoutData[currentSetConfig.setIndex].getExerciseApproachData(currentExerciseIndex, currentSetConfig.approachIndex)
        data.weight = weight
        pushCurrentExerciseApproachData()
    }

    override fun start(workoutPlanID: Long) {
        requestWorkoutPlanInfoUC(workoutPlanID)
        workoutData = mutableListOf()
        currentSetConfig = WorkoutSetConfig(-1, -1, -1, -1, -1)

        disposables.add(
                getCurrentWorkoutExercisesUC()
                        .subscribeOn(Schedulers.io())
                        .subscribe({ exercises ->
                            currentExercises = exercises
                            currentExerciseIndex = 0
                            pushCurrentWorkoutExercise()
                        }, { error ->
                            // TODO: send error
                        })
        )

        disposables.add(
                getWorkoutSetConfigUC()
                        .subscribeOn(Schedulers.io())
                        .subscribe({ config ->
                            if (config.setIndex > currentSetConfig.setIndex) {
                                workoutData.add(WorkoutSetData(config.exerciseAmount, config.approachAmount))
                            }
                            currentSetConfig = config
                            pushWorkoutExerciseConfig()
                            pushCurrentExerciseApproachData()
                        }, { error ->
                            // TODO: send error
                        })
        )
    }

    override fun stop() {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun next() {
        currentExerciseIndex++
        when {
            currentExerciseIndex < currentExercises.size -> {
                pushCurrentWorkoutExercise()
                pushWorkoutExerciseConfig()
                pushCurrentExerciseApproachData()
            }
            currentSetConfig.approachIndex + 1 < currentSetConfig.approachAmount -> {
                changeWorkoutSetConfigUC.putApproachIndex(currentSetConfig.approachIndex + 1)
            }
            currentSetConfig.setIndex + 1 < currentSetConfig.setAmount -> {
                changeWorkoutSetConfigUC.putSetIndex(currentSetConfig.setIndex + 1)
            }
            else -> {
                stop()
            }
        }
    }

    private fun pushCurrentExerciseApproachData() {
        val data = workoutData[currentSetConfig.setIndex].getExerciseApproachData(currentExerciseIndex, currentSetConfig.approachIndex)
        exerciseApproachDataSubject.onNext(data)
    }

    private fun pushCurrentWorkoutExercise() {
        val exercise = currentExercises[currentExerciseIndex]
        workoutExerciseSubject.onNext(exercise)
    }

    private fun pushWorkoutExerciseConfig() {
        val config = WorkoutExerciseConfig(
                currentSetConfig.setIndex + 1, currentExerciseIndex + 1, currentSetConfig.approachIndex + 1,
                currentSetConfig.setAmount, currentExercises.size, currentSetConfig.approachAmount
        )
        workoutExerciseConfigSubject.onNext(config)
    }
}