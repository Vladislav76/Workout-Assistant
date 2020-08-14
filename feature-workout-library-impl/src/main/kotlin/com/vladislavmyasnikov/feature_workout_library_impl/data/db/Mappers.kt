package com.vladislavmyasnikov.feature_workout_library_impl.data.db

import com.vladislavmyasnikov.common.utils.Mapper
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.*
import com.vladislavmyasnikov.feature_exercise_library_api.ExerciseInfo
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entity.*
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.CompletedWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.CompletedWorkoutResult
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.ShortCompletedWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.workout.ShortWorkout

// Short Entity -> Model
object Entity2ModelShortWorkoutMapper : Mapper<ShortWorkoutEntity, ShortWorkout>() {

    override fun map(value: ShortWorkoutEntity): ShortWorkout {
        return ShortWorkout(value.id, value.title, value.avatarID)
    }
}

// Entity -> Model
object Entity2ModelWorkoutSetMapper : Mapper<WorkoutSetEntity, WorkoutSet>() {

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
object CompletedWorkoutDomainToCompletedWorkoutResultDomainMapper : Mapper<CompletedWorkout, CompletedWorkoutResult>() {

    override fun map(value: CompletedWorkout): CompletedWorkoutResult {
        return CompletedWorkoutResult(value.duration, value.workoutProductivity)
    }
}