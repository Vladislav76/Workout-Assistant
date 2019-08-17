package com.vladislavmyasnikov.feature_exercise_library_impl.data.repo_mapper_impl

import com.vladislavmyasnikov.core_utils.utils.utils.Mapper
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ShortExerciseInfo
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.FullExerciseInfo
import com.vladislavmyasnikov.feature_exercise_library_impl.data.db.entities.ShortExerciseInfo as ShortExerciseInfoEntity
import com.vladislavmyasnikov.feature_exercise_library_impl.data.db.entities.FullExerciseInfo as FullExerciseInfoEntity

// Short Entity -> Model
object EntityToModelShortExerciseInfoMapper : Mapper<ShortExerciseInfoEntity, ShortExerciseInfo>() {

    override fun map(value: ShortExerciseInfoEntity): ShortExerciseInfo {
        return ShortExerciseInfo(value.id, value.title, value.muscleGroupsIDs)
    }
}

// Full Entity -> Model
object EntityToModelFullExerciseInfoMapper : Mapper<FullExerciseInfoEntity, FullExerciseInfo>() {

    override fun map(value: FullExerciseInfoEntity): FullExerciseInfo {
        return FullExerciseInfo(value.id, value.title, value.muscleGroupsIDs, value.description)
    }
}