package com.vladislavmyasnikov.feature_workout_library_impl.data.repository

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.feature_exercise_library_api.ExerciseLibraryInteractor
import com.vladislavmyasnikov.feature_workout_library_api.WorkoutLibraryInteractor
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.*
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutSet
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.CompletedWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.CompletedWorkoutResult
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.ShortCompletedWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.workout.ShortWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.workout.Workout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.repository.WorkoutRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class WorkoutRepositoryImpl @Inject constructor(
        private val localDataSource: LocalDatabase,
        private val exerciseLibraryInteractor: ExerciseLibraryInteractor
) : WorkoutRepository, WorkoutLibraryInteractor {

    private var lastSavedCompletedWorkoutResult: CompletedWorkoutResult? = null

    override fun fetchShortWorkoutList(): Observable<List<ShortWorkout>> {
        return localDataSource.workoutLibraryDao().loadAllWorkouts().map(Entity2ModelShortWorkoutMapper::map)
    }

    override fun fetchWorkoutById(id: Long): Single<Workout> {
        localDataSource.workoutLibraryDao().apply {
            return loadWorkoutById(id).map { workout ->
                Workout(workout.id, workout.title, workout.avatarID, workout.description)
            }
        }
    }

    override fun fetchWorkoutSetList(id: Long): Single<List<WorkoutSet>> {
        localDataSource.workoutLibraryDao().apply {
            return loadWorkoutById(id).flatMap { workout ->
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

    override fun saveCompletedWorkout(workout: CompletedWorkout): Completable {
        lastSavedCompletedWorkoutResult = CompletedWorkoutDomainToCompletedWorkoutResultDomainMapper.map(workout)
        return localDataSource.workoutLibraryDao().insertCompletedWorkout(CompletedWorkoutDomainToDatabaseMapper.map(workout))
    }

    override fun fetchLastCompletedWorkoutResult(): Single<CompletedWorkoutResult> {
        return Single.fromCallable { lastSavedCompletedWorkoutResult }
    }

    override fun fetchWorkoutNamesByIds(ids: List<Long>): List<String> {
        return localDataSource.workoutLibraryDao().loadWorkoutNamesByIds(ids)
    }

    override fun fetchAllCompletedWorkouts(): Observable<List<ShortCompletedWorkout>> {
        return localDataSource.workoutLibraryDao().loadAllCompletedWorkouts().map(ShortCompletedWorkoutDatabaseToDomainMapper::map)
    }

    override fun fetchCompletedWorkoutById(id: Long): Single<CompletedWorkout> {
        return localDataSource.workoutLibraryDao().loadCompletedWorkoutById(id).map(CompletedWorkoutDatabaseToDomainMapper::map)
    }
}