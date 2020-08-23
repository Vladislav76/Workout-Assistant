package com.vladislavmyasnikov.exercise_library_impl.data.repository

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.exercise_library_api.ExerciseInfo
import com.vladislavmyasnikov.exercise_library_api.ExerciseLibraryInteractor
import com.vladislavmyasnikov.exercise_library_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.exercise_library_impl.data.db.ExerciseDatabaseToDomainMapper
import com.vladislavmyasnikov.exercise_library_impl.data.db.ShortExerciseDatabaseToDomainMapper
import com.vladislavmyasnikov.exercise_library_impl.domain.repository.ExerciseRepository
import com.vladislavmyasnikov.exercise_library_impl.domain.entity.Exercise
import com.vladislavmyasnikov.exercise_library_impl.domain.entity.ShortExercise
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class ExerciseRepositoryImpl @Inject constructor(
        private val localDataSource: LocalDatabase
) : ExerciseRepository, ExerciseLibraryInteractor {

    override fun fetchAllExercises(): Observable<List<ShortExercise>> {
        return localDataSource.exerciseLibraryDao().loadAllExercises().map(ShortExerciseDatabaseToDomainMapper::map)
    }

    override fun fetchExerciseById(id: Long): Single<Exercise> {
        return localDataSource.exerciseLibraryDao().loadExerciseById(id).map(ExerciseDatabaseToDomainMapper::map)
    }

    override fun fetchExercises(ids: List<Long>): Single<List<ExerciseInfo>> {
        return localDataSource.exerciseLibraryDao().loadExercisesInfo(ids)
    }
}