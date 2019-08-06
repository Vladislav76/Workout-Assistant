package com.vladislavmyasnikov.feature_exercise_book_impl.data.repo_mapper_impl

import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.feature_exercise_book_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.feature_exercise_book_impl.domain.ExerciseRepository
import com.vladislavmyasnikov.feature_exercise_book_impl.domain.ShortExerciseInfo
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class ExerciseRepositoryImpl @Inject constructor(private val localDataSource: LocalDatabase) : ExerciseRepository {

    override fun fetchShortExercisesInfo(): Single<List<ShortExerciseInfo>> {
        return localDataSource.exerciseBookDao().loadShortExercisesInfo().map(EntityToModelShortExerciseInfoMapper::map)
    }
}