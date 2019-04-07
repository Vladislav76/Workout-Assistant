package com.vladislav.workoutassistant.data.db

import com.vladislav.workoutassistant.data.db.entity.*
import com.vladislav.workoutassistant.data.db.entity.Set
import java.sql.Time
import java.util.*

object DataGenerator {

    private const val EXERCISE_NUMBER = 100
    private const val CATEGORY_NUMBER = 6
    private const val INTENSITY_LEVEL_NUMBER = 3
    private const val MUSCLE_GROUP_NUMBER = 9
    private const val MINIMUM_WORKOUT_NUMBER_PER_GROUP = 10
    private const val MAXIMUM_WORKOUT_NUMBER_PER_GROUP = 20
    private const val MINIMUM_SET_NUMBER_PER_WORKOUT = 5
    private const val MAXIMUM_SET_NUMBER_PER_WORKOUT = 10
    private const val MINIMUM_EXERCISE_NUMBER_PER_SET = 3
    private const val MAXIMUM_EXERCISE_NUMBER_PER_SET = 7

    fun generateEntries(workoutNumber: Int): List<DiaryEntry> {
        val entries = ArrayList<DiaryEntry>()
        for (i in 1..workoutNumber) {
            val calendar = GregorianCalendar(0, 0, 0, 0, 0)
            val time = Time(calendar.time.time)
            val entry = DiaryEntry(startTime = time, finishTime = time, name = "Workout №$i")
            entries.add(entry)
        }
        return entries
    }

    fun generateWorkouts(): List<Workout> {
        val random = Random()
        var currentWorkoutId = 1
        val workouts = ArrayList<Workout>()
        for (categoryId in 0 until CATEGORY_NUMBER) {
            for (levelId in 0 until INTENSITY_LEVEL_NUMBER) {
                for (i in 0..random.nextInt(MAXIMUM_WORKOUT_NUMBER_PER_GROUP - MINIMUM_WORKOUT_NUMBER_PER_GROUP + 1) + MINIMUM_WORKOUT_NUMBER_PER_GROUP) {
                    val workout = Workout(name = "Workout $currentWorkoutId", categoryId = categoryId, intensityLevelId = levelId)
                    workouts.add(workout)
                    currentWorkoutId++
                }
            }
        }
        return workouts
    }

    fun generateSets(workoutNumber: Int): List<Set> {
        val random = Random()
        val sets = ArrayList<Set>()
        for (i in 1..workoutNumber) {
            for (k in 0..random.nextInt(MAXIMUM_SET_NUMBER_PER_WORKOUT - MINIMUM_SET_NUMBER_PER_WORKOUT + 1) + MINIMUM_SET_NUMBER_PER_WORKOUT) {
                val set = Set(workoutId = i, amount = random.nextInt(50))
                sets.add(set)
            }
        }
        return sets
    }

    fun generateExercises(): List<Exercise> {
        val random = Random()
        val exercises = ArrayList<Exercise>()
        for (i in 1..EXERCISE_NUMBER) {
            val exercise = Exercise(name = "Exercise $i", description = "Description $i", muscleGroupId = random.nextInt(MUSCLE_GROUP_NUMBER))
            exercises.add(exercise)
        }
        return exercises
    }

    fun generateSetAndExerciseMatching(setNumber: Int): List<SetVsExerciseMatching> {
        val random = Random()
        val matching = ArrayList<SetVsExerciseMatching>()
        for (i in 1..setNumber) {
            for (k in 0..random.nextInt(MAXIMUM_EXERCISE_NUMBER_PER_SET - MINIMUM_EXERCISE_NUMBER_PER_SET + 1) + MINIMUM_EXERCISE_NUMBER_PER_SET) {
                val entry = SetVsExerciseMatching(setId = i, exerciseId = random.nextInt(EXERCISE_NUMBER) + 1, amount = random.nextInt(999))
                matching.add(entry)
            }
        }
        return matching
    }
}
