package com.vladislavmyasnikov.feature_exercise_book_impl.data.repo_mapper_impl

import com.vladislavmyasnikov.core_utils.utils.utils.Mapper
import com.vladislavmyasnikov.feature_exercise_book_impl.domain.ShortExerciseInfo
import com.vladislavmyasnikov.feature_exercise_book_impl.domain.FullExerciseInfo
import com.vladislavmyasnikov.feature_exercise_book_impl.data.db.entities.ShortExerciseInfo as ShortExerciseInfoEntity
import com.vladislavmyasnikov.feature_exercise_book_impl.data.db.entities.FullExerciseInfo as FullExerciseInfoEntity

// Short Entity -> Model
object EntityToModelShortExerciseInfoMapper : Mapper<ShortExerciseInfoEntity, ShortExerciseInfo>() {

    override fun map(value: ShortExerciseInfoEntity): ShortExerciseInfo {
        return ShortExerciseInfo(value.id, value.title)
    }
}

// Full Entity -> Model
object EntityToModelFullExerciseInfoMapper : Mapper<FullExerciseInfoEntity, FullExerciseInfo>() {

    override fun map(value: FullExerciseInfoEntity): FullExerciseInfo {
        return FullExerciseInfo(value.id, value.title, value.description)
    }
}

// Model -> Full Entity
object ModelToEntityFullExerciseInfoMapper : Mapper<FullExerciseInfo, FullExerciseInfoEntity>() {

    override fun map(value: FullExerciseInfo): FullExerciseInfoEntity {
        return FullExerciseInfoEntity(value.id, value.title, value.description)
    }
}