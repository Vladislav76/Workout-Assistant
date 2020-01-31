package com.vladislavmyasnikov.feature_workout_library_impl.di

import android.content.Context
import androidx.room.RoomDatabase
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.ContextModule
import com.vladislavmyasnikov.common.factories.RoomDatabaseFactory
import com.vladislavmyasnikov.common.interfaces.DataSaver
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.generateSetsInfo
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.generateWorkoutsInfo
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
                    val setsIDs: MutableList<List<Long>> = mutableListOf()
                    for (i in 1..10) {
                        val ids = locDb.workoutLibraryDao().insertSetsInfo(generateSetsInfo(
                                setsPerWorkout = 1..4, exercisesPerSet = 3..7,
                                repsPerSet = 2..4, repsPerExercise = 5..150, maxExerciseID = 20
                        ))
                        setsIDs.add(ids)
                    }
                    locDb.workoutLibraryDao().insertWorkoutsInfo(generateWorkoutsInfo(setsIDs))
                }
            }) as LocalDatabase
}