package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.impl

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExerciseConfig
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutSetConfig
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.*
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@PerFeature
class ManageWorkoutPlayerUCImpl @Inject constructor(
        private val requestWorkoutPlanInfoUC: RequestWorkoutPlanInfoUC,
        private val getCurrentWorkoutExercisesUC: GetCurrentWorkoutExercisesUC,
        private val changeWorkoutSetConfigUC: ChangeWorkoutSetConfigUC,
        private val getWorkoutSetConfigUC: GetWorkoutSetConfigUC
) : GetWorkoutExerciseConfigUC, GetCurrentWorkoutExerciseUC, ManageExecutingWorkoutUC {

    private var currentExerciseIndex = -1
    private lateinit var currentSetConfig: WorkoutSetConfig
    private var currentExercises = listOf<WorkoutExercise>()
    private val workoutExerciseConfigSubject = PublishSubject.create<WorkoutExerciseConfig>()
    private val currentWorkoutExerciseSubject = PublishSubject.create<WorkoutExercise>()

    override fun invoke(): Observable<WorkoutExerciseConfig> = workoutExerciseConfigSubject

    override fun invoke(spike: Int): Observable<WorkoutExercise> = currentWorkoutExerciseSubject

    // TODO: may be remove RX-stream from here ???
    override fun start(workoutPlanID: Long) {
        requestWorkoutPlanInfoUC(workoutPlanID)

        val disposable = getCurrentWorkoutExercisesUC()
                .subscribeOn(Schedulers.io())
                .subscribe({ exercises ->
                    currentExercises = exercises
                    currentExerciseIndex = 0
                    pushCurrentWorkoutExercise()
                }, { error ->
                    // TODO: send error
                })

        val disposable2 = getWorkoutSetConfigUC()
                .subscribeOn(Schedulers.io())
                .subscribe({ config ->
                    currentSetConfig = config
                    pushWorkoutExerciseConfig()
                }, { error ->
                    // TODO: send error
                })
    }

    override fun stop() {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun next() {
        if (++currentExerciseIndex < currentExercises.size) {
            pushCurrentWorkoutExercise()
        } else if (currentSetConfig.approach + 1 < currentSetConfig.approachAmount) {
            changeWorkoutSetConfigUC.setApproach(currentSetConfig.approach + 1)
        } else if (currentSetConfig.setIndex + 1 < currentSetConfig.setAmount) {
            changeWorkoutSetConfigUC.setNumber(currentSetConfig.setIndex + 1)
        } else {
            stop()
        }
    }

    private fun pushCurrentWorkoutExercise() {
        val exercise = currentExercises[currentExerciseIndex]
        currentWorkoutExerciseSubject.onNext(exercise)
    }

    private fun pushWorkoutExerciseConfig() {
        val config = WorkoutExerciseConfig(
                currentSetConfig.setIndex, currentExerciseIndex, currentSetConfig.approach,
                currentSetConfig.setAmount, currentExercises.size, currentSetConfig.approachAmount
        )
        workoutExerciseConfigSubject.onNext(config)
    }
}