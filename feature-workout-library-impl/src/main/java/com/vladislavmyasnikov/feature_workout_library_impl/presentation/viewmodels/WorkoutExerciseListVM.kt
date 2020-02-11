package com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.common.arch_components.Event
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutRepository
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutSet
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutExerciseListVM @Inject constructor(private val repository: WorkoutRepository) : BaseViewModel<List<WorkoutExercise>, Throwable>() {

    override val label = "WORKOUT_EXERCISE_LIST_VM"

    var workoutSetNumber = -1
        set(value) {
            if (value != field) {
                field = value
                val item = workoutSets[value].workoutExercises
                workoutSetApproachAmount = item[0].reps.size
                workoutSetApproach = 0
                pushItem(item)
            }
        }

    var workoutSetApproach = -1
        set(value) {
            if (value != field) {
                field = value
                pushEvent(Event.SelectedItemIdChangeEvent(value.toLong()))
            }
        }

    var workoutSetAmount = 0
        private set

    var workoutSetApproachAmount = 0
        private set

    private var workoutSets = listOf<WorkoutSet>()

    fun fetch(workoutID: Long) {
        disposables.add(
                repository.fetchWorkoutSetList(workoutID)
                        .subscribeOn(Schedulers.io())
                        .subscribe({ item ->
                            workoutSets = item
                            workoutSetAmount = workoutSets.size
                            workoutSetNumber = 0
                        }, { error ->
                            pushError(error)
                        })
        )
    }

    fun fetchWorkoutExercise(id: Long): WorkoutExercise {
        return workoutSets[workoutSetNumber].workoutExercises.find { workoutExercise -> workoutExercise.id == id }!!
    }
}
