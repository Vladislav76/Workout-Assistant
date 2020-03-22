package com.vladislavmyasnikov.feature_workout_library_impl.data.repo_mapper_impl

import com.vladislavmyasnikov.common.utils.Mapper
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutExerciseEntity
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutSetEntity
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.models.ShortWorkoutEntity
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.*
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseInfo

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
            val approaches = workoutExercise.reps.withIndex().map { (index, reps) -> ApproachInfo(reps, workoutExercise.weights[index]) }
            WorkoutSetElement(WorkoutExerciseInfo(workoutExercise.id, exercise.title, exercise.avatar_id), approaches)
        }
        return WorkoutSet(value.id, result)
    }
}