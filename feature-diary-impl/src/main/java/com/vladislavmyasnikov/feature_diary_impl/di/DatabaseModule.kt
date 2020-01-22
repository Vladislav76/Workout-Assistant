package com.vladislavmyasnikov.feature_diary_impl.di

import android.content.Context
import androidx.room.RoomDatabase
import com.vladislavmyasnikov.common.di.modules.ContextModule
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.interfaces.DataSaver
import com.vladislavmyasnikov.common.factories.RoomDatabaseFactory
import com.vladislavmyasnikov.feature_diary_impl.data.db.*
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [ContextModule::class])
class DatabaseModule {

    @Provides
    @Named("database_name")
    @PerFeature
    fun provideDatabaseName() = "database_for_diary_feature"

    @Provides
    @PerFeature
    fun provideDatabase(@Named("application_context") context: Context, @Named("database_name") name: String): LocalDatabase =
            RoomDatabaseFactory.getInstance(context, LocalDatabase::class.java, name, object : DataSaver {
                override fun saveData(db: RoomDatabase) {
                    val locDb = db as LocalDatabase
                    locDb.diaryDao().insertEntries(generateEntries(50))
                }
            }) as LocalDatabase
}