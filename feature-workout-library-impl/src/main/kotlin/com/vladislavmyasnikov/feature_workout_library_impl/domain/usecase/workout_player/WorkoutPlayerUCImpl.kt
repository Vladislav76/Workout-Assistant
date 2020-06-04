package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.*
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller.ChangeWorkoutSetConfigUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller.GetWorkoutExerciseListUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller.GetWorkoutSetConfigUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller.RequestWorkoutUC
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@PerScreen
class WorkoutPlayerUCImpl @Inject constructor(
        private val requestWorkoutUC: RequestWorkoutUC,
        private val getWorkoutExerciseListUC: GetWorkoutExerciseListUC,
        private val changeWorkoutSetConfigUC: ChangeWorkoutSetConfigUC,
        private val getWorkoutSetConfigUC: GetWorkoutSetConfigUC
) : GetWorkoutExerciseConfigUC, GetWorkoutExerciseUC, AccessWorkoutExerciseMetricsUC, AccessWorkoutProcessStateUC {

    private var currentExerciseIndex = -1
    private lateinit var currentSetConfig: WorkoutSetConfig
    private var currentExercises = listOf<WorkoutExercise>()
    private lateinit var workoutData: MutableList<WorkoutSetData>
    private lateinit var currentWorkoutProcessState: WorkoutProcessState

    private val workoutExerciseSubject = PublishSubject.create<WorkoutExercise>()
    private val workoutExerciseConfigSubject = PublishSubject.create<WorkoutExerciseConfig>()
    private val exerciseApproachDataSubject = PublishSubject.create<WorkoutExerciseIndicators>()
    private val workoutProcessStateSubject = BehaviorSubject.create<WorkoutProcessState>()
    private val disposables = CompositeDisposable()

    override fun invoke(): Observable<WorkoutExerciseConfig> = workoutExerciseConfigSubject

    override fun invoke(spike: Int): Observable<WorkoutExercise> = workoutExerciseSubject

    override fun getMetrics(): Observable<WorkoutExerciseIndicators> = exerciseApproachDataSubject

    override fun setRepetitionMetrics(value: Int) {
        val data = workoutData[currentSetConfig.setIndex].getExerciseApproachData(currentExerciseIndex, currentSetConfig.approachIndex)
        data.reps = value
        pushCurrentExerciseApproachData()
    }

    override fun setWeightMetrics(value: Float) {
        val data = workoutData[currentSetConfig.setIndex].getExerciseApproachData(currentExerciseIndex, currentSetConfig.approachIndex)
        data.weight = value
        pushCurrentExerciseApproachData()
    }

    override fun getWorkoutProcessState(): Observable<WorkoutProcessState> = workoutProcessStateSubject

    override fun startWorkout(workoutPlanID: Long) {
        requestWorkoutUC(workoutPlanID)
        workoutData = mutableListOf()
        currentSetConfig = WorkoutSetConfig(-1, -1, -1, -1, -1)
        workoutProcessStateSubject.onNext(WorkoutProcessState.STARTED)

        disposables.add(
                getWorkoutExerciseListUC()
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
        pushWorkoutProcessState(WorkoutProcessState.STARTED)
    }

    override fun stopWorkout() {
        // TODO: add current status check
        pushWorkoutProcessState(WorkoutProcessState.FINISHED)
    }

    override fun pauseWorkout() {
        pushWorkoutProcessState(WorkoutProcessState.PAUSED)
    }

    override fun resumeWorkout() {
        pushWorkoutProcessState(WorkoutProcessState.STARTED)
    }

    override fun nextExercise() {
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
                stopWorkout()
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

    private fun pushWorkoutProcessState(state: WorkoutProcessState) {
        currentWorkoutProcessState = state
        workoutProcessStateSubject.onNext(state)
    }
}