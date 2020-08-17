package com.vladislavmyasnikov.exercise_library_impl.data.repo_mapper_impl

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.exercise_library_api.ExerciseInfo
import com.vladislavmyasnikov.exercise_library_api.ExerciseLibraryInteractor
import com.vladislavmyasnikov.exercise_library_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.exercise_library_impl.domain.repository.ExerciseRepository
import com.vladislavmyasnikov.exercise_library_impl.domain.entity.FullExercise
import com.vladislavmyasnikov.exercise_library_impl.domain.entity.ShortExercise
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class ExerciseRepositoryImpl @Inject constructor(private val localDataSource: LocalDatabase) : ExerciseRepository, ExerciseLibraryInteractor {

    override fun fetchShortExercisesInfo(): Observable<List<ShortExercise>> {
        return localDataSource.exerciseLibraryDao().loadShortExercisesInfo().map(EntityToModelShortExerciseInfoMapper::map)
    }

    override fun fetchFullExerciseInfo(id: Long): Single<FullExercise> {
        return localDataSource.exerciseLibraryDao().loadExerciseInfoById(id).map(EntityToModelFullExerciseInfoMapper::map)
    }

    override fun fetchExercises(ids: List<Long>): Single<List<ExerciseInfo>> {
        return localDataSource.exerciseLibraryDao().loadExercisesInfo(ids)
    }
}