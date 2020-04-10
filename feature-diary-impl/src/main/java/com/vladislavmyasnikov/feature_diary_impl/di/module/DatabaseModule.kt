package com.vladislavmyasnikov.feature_diary_impl.di.module

import android.content.Context
import androidx.room.RoomDatabase
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.ContextModule
import com.vladislavmyasnikov.common.factories.RoomDatabaseFactory
import com.vladislavmyasnikov.common.interfaces.DataSaver
import com.vladislavmyasnikov.feature_diary_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.feature_diary_impl.data.db.generateEntries
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [ContextModule::class])
class DatabaseModule {

    @Provides @PerFeature @Named("database_name")
    fun provide1() = "database_for_diary_feature"

    @Provides @PerFeature
    fun provide2(@Named("application_context") context: Context, @Named("database_name") name: String): LocalDatabase =
            RoomDatabaseFactory.getInstance(context, LocalDatabase::class.java, name, object : DataSaver {
                override fun saveData(db: RoomDatabase) {
                    val locDb = db as LocalDatabase
                    locDb.diaryDao().insertEntries(generateEntries(50))
                }
            }) as LocalDatabase
}