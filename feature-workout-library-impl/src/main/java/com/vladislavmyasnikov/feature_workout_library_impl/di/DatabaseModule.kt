package com.vladislavmyasnikov.feature_workout_library_impl.di

import android.content.Context
import androidx.room.RoomDatabase
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.ContextModule
import com.vladislavmyasnikov.common.factories.RoomDatabaseFactory
import com.vladislavmyasnikov.common.interfaces.DataSaver
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.generateWorkoutExerciseList
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.generateWorkoutSetList
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.generateWorkoutList
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
                    val setAmountRange = 1..7
                    val workoutSetIDs = mutableListOf<List<Long>>()

                    for (workoutNumber in 1..workoutAmountRange.random()) {
                        val workoutExerciseIDs = mutableListOf<List<Long>>()

                        for (setNumber in 1..setAmountRange.random()) {
                            val ids = locDb.workoutLibraryDao().insertWorkoutExerciseList(
                                    generateWorkoutExerciseList(exerciseAmountRange = 1..10, approachAmountRange = 1..10, exerciseIDRange = 1L..20L, repsAmountRange = 5..150, weightsRange = 5..100)
                            )
                            workoutExerciseIDs.add(ids)
                        }

                        val ids = locDb.workoutLibraryDao().insertWorkoutSetList(
                                generateWorkoutSetList(workoutExerciseIDs)
                        )
                        workoutSetIDs.add(ids)
                    }
                    locDb.workoutLibraryDao().insertWorkoutList(generateWorkoutList(workoutSetIDs))
                }
            }) as LocalDatabase
}