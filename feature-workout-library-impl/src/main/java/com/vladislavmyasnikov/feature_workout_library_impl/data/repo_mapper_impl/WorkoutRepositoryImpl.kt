package com.vladislavmyasnikov.feature_workout_library_impl.data.repo_mapper_impl

import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.feature_workout_library_impl.domain.FullWorkoutInfo
import com.vladislavmyasnikov.feature_workout_library_impl.domain.ShortWorkoutInfo
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutRepository
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryInteractor
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class WorkoutRepositoryImpl @Inject constructor(private val localDataSource: LocalDatabase, private val exerciseLibraryInteractor: ExerciseLibraryInteractor) : WorkoutRepository {

    override fun fetchShortWorkoutsInfo(): Observable<List<ShortWorkoutInfo>> {
        return localDataSource.workoutLibraryDao().loadShortWorkoutsInfo().map(EntityToModelShortWorkoutInfoMapper::map)
    }

    override fun fetchFullWorkoutInfo(id: Long): Single<FullWorkoutInfo> {
        val dao = localDataSource.workoutLibraryDao()
        return dao.loadWorkoutByID(id).flatMap { workout ->
            dao.loadSetsByIDs(workout.setsIDs).flatMap { sets ->
                val exercisesIDs = sets.map { set -> set.exercisesIDs }.flatten().distinct()
                exerciseLibraryInteractor.fetchWorkoutExercisesInfo(exercisesIDs).map { info ->
                    EntityToModelSetInfoMapper.exercises = info
                    FullWorkoutInfo(workout.id, workout.title, workout.description, EntityToModelSetInfoMapper.map(sets))
                }
            }
        }
    }
}