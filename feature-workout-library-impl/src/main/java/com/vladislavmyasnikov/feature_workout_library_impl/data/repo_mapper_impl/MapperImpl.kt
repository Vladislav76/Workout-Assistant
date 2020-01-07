package com.vladislavmyasnikov.feature_workout_library_impl.data.repo_mapper_impl

import com.vladislavmyasnikov.common.utils.Mapper
import com.vladislavmyasnikov.feature_workout_library_impl.domain.ExerciseInfo
import com.vladislavmyasnikov.feature_workout_library_impl.domain.FullWorkoutInfo
import com.vladislavmyasnikov.feature_workout_library_impl.domain.SetInfo
import com.vladislavmyasnikov.feature_workout_library_impl.domain.ShortWorkoutInfo
import com.vladislavmyasnikov.features_api.exercise_library.WorkoutExerciseInfo
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.models.ShortWorkoutInfo as ShortWorkoutInfoEntity
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.SetInfo as SetInfoEntity

// Short Entity -> Model
object EntityToModelShortWorkoutInfoMapper : Mapper<ShortWorkoutInfoEntity, ShortWorkoutInfo>() {

    override fun map(value: ShortWorkoutInfoEntity): ShortWorkoutInfo {
        return ShortWorkoutInfo(value.id, value.title, value.avatarID)
    }
}

// Entity -> Model
object EntityToModelSetInfoMapper : Mapper<SetInfoEntity, SetInfo>() {

    var exercises: List<WorkoutExerciseInfo> = emptyList()

    override fun map(value: SetInfoEntity): SetInfo {
        val exercisesInfo = value.exercisesReps.mapIndexed { index, list ->
            val exercise = exercises.find { it.id == value.exercisesIDs[index] }
            ExerciseInfo(value.exercisesIDs[index], exercise?.title ?: "", list, exercise?.avatar_id ?: "")
        }
        return SetInfo(value.id, exercisesInfo)
    }
}