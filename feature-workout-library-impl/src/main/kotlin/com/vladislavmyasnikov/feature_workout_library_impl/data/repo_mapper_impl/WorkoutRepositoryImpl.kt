package com.vladislavmyasnikov.feature_workout_library_impl.data.repo_mapper_impl

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.FullWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.ShortWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutSet
import com.vladislavmyasnikov.feature_workout_library_impl.domain.repository.WorkoutRepository
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryInteractor
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class WorkoutRepositoryImpl @Inject constructor(
        private val localDataSource: LocalDatabase,
        private val exerciseLibraryInteractor: ExerciseLibraryInteractor
) : WorkoutRepository {

    override fun fetchShortWorkoutList(): Observable<List<ShortWorkout>> {
        return localDataSource.workoutLibraryDao().loadShortWorkoutList().map(Entity2ModelShortWorkoutMapper::map)
    }

    override fun fetchFullWorkout(id: Long): Single<FullWorkout> {
        localDataSource.workoutLibraryDao().apply {
            return loadWorkout(id).map { workout ->
                FullWorkout(workout.id, workout.title, workout.avatarID, workout.description)
            }
        }
    }

    override fun fetchWorkoutSetList(id: Long): Single<List<WorkoutSet>> {
        localDataSource.workoutLibraryDao().apply {
            return loadWorkout(id).flatMap { workout ->
                loadWorkoutSetList(workout.workoutSetIDs).flatMap { workoutSets ->
                    val workoutExerciseIDs = workoutSets.map { workoutSet -> workoutSet.workoutExerciseIDs }.flatten()

                    loadWorkoutExerciseList(workoutExerciseIDs).flatMap { workoutExercises ->
                        val exerciseIDs = workoutExercises.map { workoutExercise -> workoutExercise.exerciseId }

                        exerciseLibraryInteractor.fetchExercises(exerciseIDs).map { exercises ->
                            Entity2ModelWorkoutSetMapper.exercises = exercises
                            Entity2ModelWorkoutSetMapper.workoutExercises = workoutExercises
                            Entity2ModelWorkoutSetMapper.map(workoutSets)
                        }
                    }
                }
            }
        }
    }
}