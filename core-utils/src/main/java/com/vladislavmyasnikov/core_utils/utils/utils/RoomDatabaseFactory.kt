package com.vladislavmyasnikov.core_utils.utils.utils

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vladislavmyasnikov.core_utils.utils.interfaces.DataSaver

object RoomDatabaseFactory {

    const val tag = "ROOM_DATABASE_FACTORY"
    private val instances: HashMap<Class<out RoomDatabase>, RoomDatabase?> = HashMap()

    fun getInstance(context: Context, databaseClass: Class<out RoomDatabase>, name: String, dataLoader: DataSaver): RoomDatabase =
            instances[databaseClass] ?: synchronized(this) {
                instances[databaseClass] ?: buildDatabase(context, databaseClass, name, dataLoader).also { instances[databaseClass] = it }
            }

    private fun buildDatabase(context: Context, databaseClass: Class<out RoomDatabase>, name: String, dataLoader: DataSaver) =
            Room.databaseBuilder(context, databaseClass, name)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Thread {
                                Logger.debug(tag, "Database creation: DATA SAVING IS STARTED; Class: $databaseClass")
                                dataLoader.saveData(instances[databaseClass]!!)
                                Logger.debug(tag, "Database creation: DATA SAVING IS FINISHED; Class: $databaseClass")
                            }.start()
                        }
                    })
                    .build()
}