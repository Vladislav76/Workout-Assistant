package com.vladislavmyasnikov.workout_library_and_player_impl.data.db

import com.vladislavmyasnikov.common.utils.Mapper
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.*
import com.vladislavmyasnikov.exercise_library_api.ExerciseInfo
import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.CompletedWorkout
import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.ShortCompletedWorkout
import com.vladislavmyasnikov.workout_library_and_player_impl.data.db.entity.*
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_execution.WorkoutResult
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout.ShortWorkout

// DATABASE -> DOMAIN
object ShortWorkoutDatabaseToDomainMapper : Mapper<ShortWorkoutEntity, ShortWorkout>() {

    override fun map(value: ShortWorkoutEntity): ShortWorkout {
        return ShortWorkout(value.id, value.title, value.avatarID)
    }
}

// DATABASE -> DOMAIN
object WorkoutSetDatabaseToDomainMapper : Mapper<WorkoutSetEntity, WorkoutSet>() {

    var exercises = listOf<ExerciseInfo>()
    var workoutExercises = listOf<WorkoutExerciseEntity>()

    override fun map(value: WorkoutSetEntity): WorkoutSet {
        val result = value.workoutExerciseIDs.map { id ->
            val workoutExercise = workoutExercises.find { entity -> entity.id == id }!!
            val exercise = exercises.find { entity -> entity.id == workoutExercise.exerciseId }!!
            val approaches = workoutExercise.reps.withIndex().map { (index, reps) -> WorkoutExerciseIndicators(reps, workoutExercise.weights[index]) }
            WorkoutSetElement(WorkoutExerciseInfo(workoutExercise.id, exercise.title, exercise.avatar_id), approaches)
        }
        return WorkoutSet(value.id, result)
    }
}

// DATABASE -> DOMAIN
object ShortCompletedWorkoutDatabaseToDomainMapper : Mapper<ShortCompletedWorkoutEntity, ShortCompletedWorkout>() {

    override fun map(value: ShortCompletedWorkoutEntity): ShortCompletedWorkout {
        return ShortCompletedWorkout(value.id, value.date, value.duration, value.workoutProductivity, value.workoutName)
    }
}

// DATABASE -> DOMAIN
object CompletedWorkoutDatabaseToDomainMapper : Mapper<CompletedWorkoutEntity, CompletedWorkout>() {

    override fun map(value: CompletedWorkoutEntity): CompletedWorkout {
        return CompletedWorkout(value.id, value.date, value.startTime, value.endTime, value.duration, value.description, value.workoutProductivity, value.workoutId, value.workoutName)
    }
}

// DOMAIN -> DATABASE
object CompletedWorkoutDomainToDatabaseMapper : Mapper<CompletedWorkout, CompletedWorkoutEntity>() {

    override fun map(value: CompletedWorkout): CompletedWorkoutEntity {
        return CompletedWorkoutEntity(value.id, value.date, value.startTime, value.endTime, value.duration, value.description, value.workoutProductivity, value.workoutId)
    }
}

// DOMAIN -> DOMAIN
object CompletedWorkoutDomainToWorkoutResultDomainMapper : Mapper<CompletedWorkout, WorkoutResult>() {

    override fun map(value: CompletedWorkout): WorkoutResult {
        return WorkoutResult(value.duration, value.workoutProductivity)
    }
}