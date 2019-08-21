package com.vladislavmyasnikov.feature_exercise_library_impl.data.repo_mapper_impl

import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.feature_exercise_library_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ExerciseRepository
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.FullExerciseInfo
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ShortExerciseInfo
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class ExerciseRepositoryImpl @Inject constructor(private val localDataSource: LocalDatabase) : ExerciseRepository {

    override fun fetchShortExercisesInfo(): Observable<List<ShortExerciseInfo>> {
        return localDataSource.exerciseLibraryDao().loadShortExercisesInfo().map(EntityToModelShortExerciseInfoMapper::map)
    }

    override fun fetchFullExerciseInfo(id: Long): Single<FullExerciseInfo> {
        return localDataSource.exerciseLibraryDao().loadExerciseInfoById(id).map(EntityToModelFullExerciseInfoMapper::map)
    }
}