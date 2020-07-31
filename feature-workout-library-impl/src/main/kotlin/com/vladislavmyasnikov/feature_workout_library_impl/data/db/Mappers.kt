package com.vladislavmyasnikov.feature_workout_library_impl.data.db

import com.vladislavmyasnikov.common.utils.Mapper
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entity.WorkoutExerciseEntity
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entity.WorkoutSetEntity
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.model.ShortWorkoutEntity
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.*
import com.vladislavmyasnikov.feature_exercise_library_api.ExerciseInfo

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