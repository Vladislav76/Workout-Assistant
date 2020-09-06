package com.vladislavmyasnikov.workout_library_and_player_impl.presentation

import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutExerciseCycle
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutSet

fun WorkoutExerciseCycle.textOfReps() = "x${data.reps}"
fun WorkoutExerciseCycle.textOfWeight() = "${data.weight} kg"
fun WorkoutExerciseCycle.textOfRepsAndWeight() = "${textOfWeight()}   ${textOfReps()}"
fun WorkoutExerciseCycle.textOfRepsAndWeight(index: Int) = "$index.   ${textOfRepsAndWeight()}"
fun WorkoutExerciseCycle.textOfRepsAndWeightInTwoLines(index: Int) = "$index. ${textOfWeight()}\n${textOfReps()}"

fun WorkoutSet.textOfCycles() = "x$cycles"
fun WorkoutSet.textOfExercises() = exercises.withIndex().joinToString(separator = "\n", transform = { "${it.index + 1}. ${it.value.name}" })
