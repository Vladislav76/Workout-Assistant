package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_creation

import com.vladislavmyasnikov.common.di.annotations.PerFlow
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutExerciseIndicators
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutExercise
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutExerciseCycle
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutSet
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@PerFlow
class WorkoutCreationUCImpl @Inject constructor(

) : AccessToWorkoutDataUC, ChangeWorkoutStructureUC {

    private lateinit var sets: MutableList<WorkoutSet>
    private var currentSetId = 0L
    private var currentExerciseId = 0L

    private val disposables = CompositeDisposable()
    private val setsSubject = BehaviorSubject.create<List<WorkoutSet>>()
    private val exercisesSubject = BehaviorSubject.create<List<WorkoutExercise>>()
    private val cyclesSubject = BehaviorSubject.create<List<WorkoutExerciseCycle>>()

    override fun getAllSets(): Observable<List<WorkoutSet>> {
        createEmptyWorkout()
        return setsSubject
    }

    override fun getExercisesBySetId(id: Long): Observable<List<WorkoutExercise>> {
        currentSetId = id
        exercisesSubject.onNext(sets[id.toInt()].exercises)
        return exercisesSubject
    }

    override fun getCyclesByExerciseId(id: Long): Observable<List<WorkoutExerciseCycle>> {
        currentExerciseId = id
        cyclesSubject.onNext(sets[currentSetId.toInt()].exercises[id.toInt()].cycles)
        return cyclesSubject
    }

    override fun getCycleById(id: Long): WorkoutExerciseCycle {
        return sets[currentSetId.toInt()].exercises[currentExerciseId.toInt()].cycles[id.toInt()]
    }

    override fun updateCycleById(id: Long, updatedData: WorkoutExerciseIndicators) {
        with (sets[currentSetId.toInt()].exercises[currentExerciseId.toInt()]) {
            val newData = WorkoutExerciseCycle(cycles[id.toInt()].id , updatedData)
            (cycles as MutableList)[id.toInt()] = newData
        }
    }

    // TODO: why is this needed?
    override fun createEmptyWorkout() {
        disposables.clear()
        sets = mutableListOf(
                WorkoutSet(0L,
                        mutableListOf(
                                WorkoutExercise(
                                        0L, "Goblet Squad", "work_dummy_1",
                                        mutableListOf(
                                                WorkoutExerciseCycle(0L, WorkoutExerciseIndicators(10, 15F)),
                                                WorkoutExerciseCycle(1L, WorkoutExerciseIndicators(10, 15F)),
                                                WorkoutExerciseCycle(2L, WorkoutExerciseIndicators(10, 15F)),
                                                WorkoutExerciseCycle(3L, WorkoutExerciseIndicators(10, 15F)),
                                        )
                                ),
                                WorkoutExercise(
                                        1L, "Dumbbell Clean", "work_dummy_2",
                                        mutableListOf(
                                                WorkoutExerciseCycle(0L, WorkoutExerciseIndicators(20, 15F)),
                                                WorkoutExerciseCycle(1L, WorkoutExerciseIndicators(20, 15F)),
                                                WorkoutExerciseCycle(2L, WorkoutExerciseIndicators(20, 15F)),
                                                WorkoutExerciseCycle(3L, WorkoutExerciseIndicators(20, 15F)),
                                        )
                                )),
                        2)
        )
        setsSubject.onNext(sets)
    }

    override fun addEmptySet(cycleAmount: Int): Long {
        val newId = sets.size.toLong()
        val newData = WorkoutSet(newId, mutableListOf(), cycleAmount)
        sets.add(newData)
        return newId
    }

    override fun removeSetById(id: Long) {
        sets.removeAt(id.toInt())
    }

    override fun addExerciseToSet(setId: Long, exerciseId: Long) {
        val newId = sets[setId.toInt()].exercises.size.toLong()
        val newData = WorkoutExercise(newId, "new exercise", "work_dummy_2", mutableListOf())
        (sets[setId.toInt()].exercises as MutableList).add(newData)
    }

    override fun removeExerciseFromSet(setId: Long, exerciseId: Long) {
        (sets[setId.toInt()].exercises as MutableList).removeAt(exerciseId.toInt())
    }

    override fun addCycleToSet(setId: Long) {
        val newSet = with(sets[setId.toInt()]) {
            val newId = sets[setId.toInt()].cycles.toLong()
            for (ex in exercises) {
                val newData = WorkoutExerciseCycle(newId, WorkoutExerciseIndicators(0, 0F))
                (ex.cycles as MutableList).add(newData)
            }
            WorkoutSet(id, exercises, cycles + 1)
        }
        sets[setId.toInt()] = newSet
    }

    override fun removeLastCycleFromSet(setId: Long) {
        val newSet = with(sets[setId.toInt()]) {
            for (ex in exercises) {
                (ex.cycles as MutableList).removeLast()
            }
            WorkoutSet(id, exercises, cycles - 1)
        }
        sets[setId.toInt()] = newSet
    }

    override fun addCyclesToSet(setId: Long, cycleAmount: Int) {
        repeat(cycleAmount) { addCycleToSet(setId) }
    }

    override fun removeLastCyclesFromSet(setId: Long, cycleAmount: Int) {
        repeat(cycleAmount) { removeLastCycleFromSet(setId) }
    }
}