package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_player

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.common.models.TimePoint
import com.vladislavmyasnikov.common.utils.getCurrentTimePoint
import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.CompletedWorkout
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.*
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_execution.TimerValue
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_execution.WorkoutProcessState
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.repository.WorkoutRepository
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_set_controller.ChangeWorkoutSetConfigUC
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_set_controller.GetCurrentWorkoutExerciseListUC
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_set_controller.GetCurrentWorkoutSetConfigUC
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_set_controller.RequestWorkoutUC
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timer

@PerScreen
class WorkoutPlayerUCImpl @Inject constructor(
        private val workoutRepository: WorkoutRepository,
        private val requestWorkoutUC: RequestWorkoutUC,
        private val getCurrentWorkoutExerciseListUC: GetCurrentWorkoutExerciseListUC,
        private val changeWorkoutSetConfigUC: ChangeWorkoutSetConfigUC,
        private val getCurrentWorkoutSetConfigUC: GetCurrentWorkoutSetConfigUC
) : GetCurrentWorkoutExerciseConfigUC, GetCurrentWorkoutExerciseUC, AccessWorkoutExerciseMetricsUC, ManageWorkoutProcessUC,
        GetCurrentWorkoutTimerValueUC, SaveCompletedWorkoutUC {

    private var currentWorkoutId = -1L
    private var currentExerciseIndex = -1
    private lateinit var currentSetConfig: WorkoutSetConfig
    private var currentExercises = listOf<WorkoutExercise>()
    private lateinit var workoutData: MutableList<WorkoutSetData>
    private lateinit var currentWorkoutProcessState: WorkoutProcessState

    private val workoutExerciseSubject = PublishSubject.create<WorkoutExercise>()
    private val workoutExerciseConfigSubject = PublishSubject.create<WorkoutExerciseConfig>()
    private val exerciseApproachDataSubject = PublishSubject.create<WorkoutExerciseIndicators>()
    private val workoutProcessStateSubject = BehaviorSubject.create<WorkoutProcessState>()
    private val timerValueSubject = BehaviorSubject.create<TimerValue>()
    private val disposables = CompositeDisposable()

    private var timer: Int = -1
    private lateinit var startTime: TimePoint
    private lateinit var endTime: TimePoint
    private var isResumed = false
    private var isStopped = false

    private val incrementTimer = Runnable {
            if (isResumed) {
                timer++
                val hours = timer / 3600
                val minutes = timer % 3600 / 60
                val seconds = timer % 60
                timerValueSubject.onNext(TimerValue(hours, minutes, seconds))
            }
    }

    /* * * PUBLIC API * * */
    override fun getCurrentWorkoutExerciseConfig(): Observable<WorkoutExerciseConfig> = workoutExerciseConfigSubject

    override fun getCurrentWorkoutExercise(): Observable<WorkoutExercise> = workoutExerciseSubject

    override fun getCurrentWorkoutTimerValue(): Observable<TimerValue> = timerValueSubject

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

    override fun getCurrentWorkoutProcessState(): Observable<WorkoutProcessState> = workoutProcessStateSubject

    override fun startWorkoutById(id: Long) {
        currentWorkoutId = id
        requestWorkoutUC.requestWorkoutById(id)
        workoutData = mutableListOf()
        currentSetConfig = WorkoutSetConfig(-1, -1, -1, -1, -1)
        workoutProcessStateSubject.onNext(WorkoutProcessState.STARTED)

        getCurrentWorkoutExerciseListUC.getCurrentWorkoutExercises()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { exercises ->
                            currentExercises = exercises
                            currentExerciseIndex = 0
                            pushCurrentWorkoutExercise()
                        },
                        { error -> /* TODO: send error */ }
                )
                .also { disposable -> disposables.add(disposable) }

        getCurrentWorkoutSetConfigUC.getCurrentWorkoutSetConfig()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { config ->
                            if (config.setIndex > currentSetConfig.setIndex) {
                                workoutData.add(WorkoutSetData(config.exerciseAmount, config.approachAmount))
                            }
                            currentSetConfig = config
                            pushWorkoutExerciseConfig()
                            pushCurrentExerciseApproachData()
                        },
                        { error -> /* TODO: send error */ }
                )
                .also { disposable -> disposables.add(disposable) }

        pushWorkoutProcessState(WorkoutProcessState.STARTED)
        startTime = getCurrentTimePoint()
        timer(daemon = true, initialDelay = 0, period = 1000, action = { incrementTimer.run(); if (isStopped) cancel() })
    }

    override fun stopWorkout() {
        // TODO: add current status check
        endTime = getCurrentTimePoint()
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
            else -> { stopWorkout() }
        }
    }

    override fun saveCompletedWorkout(): Completable {
        // TODO: calculate workout productivity
        val workout = CompletedWorkout(0, Date(), startTime, endTime, TimePoint(timer * 1000L),"TBD", 50, currentWorkoutId, "")
        return workoutRepository.saveCompletedWorkout(workout)
    }

    /* * * PRIVATE API * * */
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
        when (state) {
            WorkoutProcessState.STARTED -> { isResumed = true }
            WorkoutProcessState.PAUSED -> { isResumed = false }
            WorkoutProcessState.FINISHED -> { isResumed = false; isStopped = true }
        }
        currentWorkoutProcessState = state
        workoutProcessStateSubject.onNext(state)
    }
}