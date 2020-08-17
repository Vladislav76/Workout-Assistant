package com.vladislavmyasnikov.workout_library_and_player_impl.di.module

import android.content.Context
import androidx.room.RoomDatabase
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.ContextModule
import com.vladislavmyasnikov.common.factories.RoomDatabaseFactory
import com.vladislavmyasnikov.common.interfaces.DataSaver
import com.vladislavmyasnikov.workout_library_and_player_impl.data.db.*
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [ContextModule::class])
class DatabaseModule {

    @Provides @PerFeature @Named("database_name")
    fun provide1() = "database_for_workout_library_feature"

    @Provides @PerFeature
    fun provide2(@Named("application_context") context: Context, @Named("database_name") name: String): LocalDatabase =
            RoomDatabaseFactory.getInstance(context, LocalDatabase::class.java, name, object : DataSaver {
                override fun saveData(db: RoomDatabase) {
                    val locDb = db as LocalDatabase
                    val workoutAmountRange = 10..20
                    val workoutSetAmountRange = 1..7
                    val workoutSetApproachAmountRange = 1..10
                    val workoutSetIDs = mutableListOf<List<Long>>()

                    for (workoutNumber in 1..workoutAmountRange.random()) {
                        val workoutExerciseIDs = mutableListOf<List<Long>>()

                        for (setNumber in 1..workoutSetAmountRange.random()) {
                            val ids = locDb.workoutLibraryDao().insertWorkoutExerciseList(
                                    generateWorkoutExerciseList(exerciseAmountRange = 1..10, approachAmount = workoutSetApproachAmountRange.random(), exerciseIDRange = 1L..20L, repsAmountRange = 5..150, weightsRange = 5..100)
                            )
                            workoutExerciseIDs.add(ids)
                        }

                        val ids = locDb.workoutLibraryDao().insertWorkoutSetList(
                                generateWorkoutSetList(workoutExerciseIDs)
                        )
                        workoutSetIDs.add(ids)
                    }
                    val ids = locDb.workoutLibraryDao().insertWorkoutList(generateWorkoutList(workoutSetIDs))
                    locDb.workoutLibraryDao().insertCompletedWorkouts(generateCompletedWorkouts(50, ids))
                }
            }) as LocalDatabase
}