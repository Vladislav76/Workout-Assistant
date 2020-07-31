package com.vladislavmyasnikov.feature_workout_library_impl.data.repository

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.feature_diary_api.DiaryInteractor
import com.vladislavmyasnikov.feature_diary_api.domain.entity.FullDiaryEntry
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.Entity2ModelShortWorkoutMapper
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.Entity2ModelWorkoutSetMapper
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.FullWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.ShortWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutSet
import com.vladislavmyasnikov.feature_workout_library_impl.domain.repository.WorkoutRepository
import com.vladislavmyasnikov.feature_exercise_library_api.ExerciseLibraryInteractor
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class WorkoutRepositoryImpl @Inject constructor(
        private val localDataSource: LocalDatabase,
        private val exerciseLibraryInteractor: ExerciseLibraryInteractor,
        private val diaryInteractor: DiaryInteractor
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

    override fun saveWorkoutResult(result: FullDiaryEntry): Completable {
        return diaryInteractor.saveEntry(result)
    }
}