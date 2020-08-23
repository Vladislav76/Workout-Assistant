package com.vladislavmyasnikov.exercise_library_impl.data.db

import com.vladislavmyasnikov.common.interfaces.Mappable
import com.vladislavmyasnikov.common.utils.Mapper
import com.vladislavmyasnikov.exercise_library_impl.domain.entity.ShortExercise
import com.vladislavmyasnikov.exercise_library_impl.domain.entity.Exercise
import com.vladislavmyasnikov.exercise_library_impl.data.db.entity.ShortExerciseEntity
import com.vladislavmyasnikov.exercise_library_impl.data.db.entity.ExerciseEntity

// DATABASE -> DOMAIN
object ShortExerciseDatabaseToDomainMapper : Mapper<ShortExerciseEntity, ShortExercise>() {

    override fun map(value: ShortExerciseEntity): ShortExercise {
        return ShortExercise(value.id, value.title, value.muscleGroupsIDs, value.avatarID)
    }
}

// DATABASE -> DOMAIN
object ExerciseDatabaseToDomainMapper : Mapper<ExerciseEntity, Exercise>() {

    override fun map(value: ExerciseEntity): Exercise {
        return Exercise(value.id, value.title, value.muscleGroupsIDs, value.avatarID, value.imagesIDs, value.description)
    }
}

val kk = Mappable<ExerciseEntity, Exercise> {
    value -> Exercise(value.id, value.title, value.muscleGroupsIDs, value.avatarID, value.imagesIDs, value.description)
}