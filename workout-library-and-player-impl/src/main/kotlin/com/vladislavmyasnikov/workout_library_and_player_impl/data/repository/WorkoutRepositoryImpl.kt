package com.vladislavmyasnikov.workout_library_and_player_impl.data.repository

import com.vladislavmyasnikov.common.di.annotations.PerFlow
import com.vladislavmyasnikov.common.utils.Logger
import com.vladislavmyasnikov.exercise_library_api.ExerciseLibraryInteractor
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutLibraryInteractor
import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.CompletedWorkout
import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.ShortCompletedWorkout
import com.vladislavmyasnikov.workout_library_and_player_impl.data.db.*
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutSet
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_execution.WorkoutResult
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout.ShortWorkout
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout.Workout
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.repository.WorkoutRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@PerFlow
class WorkoutRepositoryImpl @Inject constructor(
        private val localDataSource: LocalDatabase,
        private val exerciseLibraryInteractor: ExerciseLibraryInteractor
) : WorkoutRepository, WorkoutLibraryInteractor {

    private var lastSavedWorkoutResult: WorkoutResult? = null

    override fun fetchShortWorkoutList(): Observable<List<ShortWorkout>> {
        return localDataSource.workoutLibraryDao().loadAllWorkouts().map(ShortWorkoutDatabaseToDomainMapper::map)
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
                            WorkoutSetDatabaseToDomainMapper.exercises = exercises
                            WorkoutSetDatabaseToDomainMapper.workoutExercises = workoutExercises
                            WorkoutSetDatabaseToDomainMapper.map(workoutSets)
                        }
                    }
                }
            }
        }
    }

    override fun saveCompletedWorkout(workout: CompletedWorkout): Completable {
        Logger.debug(this::class, "Completed workout saving...")
        lastSavedWorkoutResult = CompletedWorkoutDomainToWorkoutResultDomainMapper.map(workout)
        return localDataSource.workoutLibraryDao().insertCompletedWorkout(CompletedWorkoutDomainToDatabaseMapper.map(workout))
    }

    override fun fetchLastWorkoutResult(): Single<WorkoutResult> {
        Logger.debug(this::class, "Completed workout fetching...")
        return Single.fromCallable { lastSavedWorkoutResult }
    }

    override fun fetchAllCompletedWorkouts(): Observable<List<ShortCompletedWorkout>> {
        return localDataSource.workoutLibraryDao().loadAllCompletedWorkouts().map(ShortCompletedWorkoutDatabaseToDomainMapper::map)
    }

    override fun fetchCompletedWorkoutById(id: Long): Single<CompletedWorkout> {
        return localDataSource.workoutLibraryDao().loadCompletedWorkoutById(id).map(CompletedWorkoutDatabaseToDomainMapper::map)
    }
}