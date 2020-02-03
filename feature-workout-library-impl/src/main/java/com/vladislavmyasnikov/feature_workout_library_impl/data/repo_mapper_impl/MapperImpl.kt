package com.vladislavmyasnikov.feature_workout_library_impl.data.repo_mapper_impl

import com.vladislavmyasnikov.common.utils.Mapper
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutExerciseEntity
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutSetEntity
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutSet
import com.vladislavmyasnikov.feature_workout_library_impl.domain.ShortWorkout
import com.vladislavmyasnikov.features_api.exercise_library.WorkoutExerciseInfo
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.models.ShortWorkoutEntity

// Short Entity -> Model
object Entity2ModelShortWorkoutMapper : Mapper<ShortWorkoutEntity, ShortWorkout>() {

    override fun map(value: ShortWorkoutEntity): ShortWorkout {
        return ShortWorkout(value.id, value.title, value.avatarID)
    }
}

// Entity -> Model
object Entity2ModelWorkoutSetMapper : Mapper<WorkoutSetEntity, WorkoutSet>() {

    var exercises = listOf<WorkoutExerciseInfo>()
    var workoutExercises = listOf<WorkoutExerciseEntity>()

    override fun map(value: WorkoutSetEntity): WorkoutSet {
        val workoutExerciseList = value.workoutExerciseIDs.map { id ->
            val workoutExercise = workoutExercises.find { entity -> entity.id == id }!!
            val exercise = exercises.find { entity -> entity.id == workoutExercise.exerciseId }!!
            WorkoutExercise(workoutExercise.id, exercise.title, workoutExercise.reps, workoutExercise.weights, exercise.avatar_id)
        }
        return WorkoutSet(value.id, workoutExerciseList)
    }
}