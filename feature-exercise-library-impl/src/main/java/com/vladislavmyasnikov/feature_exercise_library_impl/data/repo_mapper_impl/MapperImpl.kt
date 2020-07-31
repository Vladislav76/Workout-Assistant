package com.vladislavmyasnikov.feature_exercise_library_impl.data.repo_mapper_impl

import com.vladislavmyasnikov.common.utils.Mapper
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.entity.ShortExercise
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.entity.FullExercise
import com.vladislavmyasnikov.feature_exercise_library_impl.data.db.entities.ShortExerciseInfo as ShortExerciseInfoEntity
import com.vladislavmyasnikov.feature_exercise_library_impl.data.db.entities.FullExerciseInfo as FullExerciseInfoEntity

// Short Entity -> Model
object EntityToModelShortExerciseInfoMapper : Mapper<ShortExerciseInfoEntity, ShortExercise>() {

    override fun map(value: ShortExerciseInfoEntity): ShortExercise {
        return ShortExercise(value.id, value.title, value.muscleGroupsIDs, value.avatarID)
    }
}

// Full Entity -> Model
object EntityToModelFullExerciseInfoMapper : Mapper<FullExerciseInfoEntity, FullExercise>() {

    override fun map(value: FullExerciseInfoEntity): FullExercise {
        return FullExercise(value.id, value.title, value.muscleGroupsIDs, value.avatarID, value.imagesIDs, value.description)
    }
}