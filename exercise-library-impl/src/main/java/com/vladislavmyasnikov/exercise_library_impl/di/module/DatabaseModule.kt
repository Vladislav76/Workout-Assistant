package com.vladislavmyasnikov.exercise_library_impl.di.module

import android.content.Context
import androidx.room.RoomDatabase
import com.vladislavmyasnikov.common.di.annotations.PerFlow
import com.vladislavmyasnikov.common.di.modules.ContextModule
import com.vladislavmyasnikov.common.factories.RoomDatabaseFactory
import com.vladislavmyasnikov.common.interfaces.DataSaver
import com.vladislavmyasnikov.exercise_library_impl.R
import com.vladislavmyasnikov.exercise_library_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.exercise_library_impl.data.db.generateExercisesInfo
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [ContextModule::class])
class DatabaseModule {

    @Provides @PerFlow @Named("database_name")
    fun provide1() = "database_for_exercise_library_feature"

    @Provides @PerFlow
    fun provide2(@Named("application_context") context: Context, @Named("database_name") name: String): LocalDatabase =
            RoomDatabaseFactory.getInstance(context, LocalDatabase::class.java, name, object : DataSaver {
                override fun saveData(db: RoomDatabase) {
                    val locDb = db as LocalDatabase
                    val muscleGroupsAmount = context.resources.getStringArray(R.array.muscle_groups).size
                    locDb.exerciseLibraryDao().insertExercises(generateExercisesInfo(30, muscleGroupsAmount))
                }
            }) as LocalDatabase
}