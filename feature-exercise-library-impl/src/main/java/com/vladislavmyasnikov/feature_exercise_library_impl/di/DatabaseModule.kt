package com.vladislavmyasnikov.feature_exercise_library_impl.di

import android.content.Context
import androidx.room.RoomDatabase
import com.vladislavmyasnikov.common.di.ContextModule
import com.vladislavmyasnikov.common.di.PerFeature
import com.vladislavmyasnikov.common.interfaces.DataSaver
import com.vladislavmyasnikov.common.components.RoomDatabaseFactory
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.feature_exercise_library_impl.data.db.generateExercisesInfo
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [ContextModule::class])
class DatabaseModule {

    @Provides
    @Named("database_name")
    @PerFeature
    fun provideDatabaseName() = "database_for_exercise_library_feature"

    @Provides
    @PerFeature
    fun provideDatabase(@Named("application_context") context: Context, @Named("database_name") name: String): LocalDatabase =
            RoomDatabaseFactory.getInstance(context, LocalDatabase::class.java, name, object : DataSaver {
                override fun saveData(db: RoomDatabase) {
                    val locDb = db as LocalDatabase
                    val muscleGroupsAmount = context.resources.getStringArray(R.array.muscle_groups).size
                    locDb.exerciseLibraryDao().insertExercisesInfo(generateExercisesInfo(30, muscleGroupsAmount))
                }
            }) as LocalDatabase
}