package com.vladislavmyasnikov.common.factories

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

object RoomDatabaseFactory {

    const val tag = "ROOM_DATABASE_FACTORY"
    private val instances: HashMap<Class<out RoomDatabase>, RoomDatabase?> = HashMap()

    fun getInstance(context: Context, databaseClass: Class<out RoomDatabase>, name: String, dataLoader: com.vladislavmyasnikov.common.interfaces.DataSaver): RoomDatabase =
            instances[databaseClass] ?: synchronized(this) {
                instances[databaseClass] ?: buildDatabase(context, databaseClass, name, dataLoader).also { instances[databaseClass] = it }
            }

    private fun buildDatabase(context: Context, databaseClass: Class<out RoomDatabase>, name: String, dataSaver: com.vladislavmyasnikov.common.interfaces.DataSaver) =
            Room.databaseBuilder(context, databaseClass, name)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Thread {
                                com.vladislavmyasnikov.common.utils.Logger.debug(tag, "Database creation: DATA SAVING IS STARTED; Class: $databaseClass")
                                dataSaver.saveData(instances[databaseClass]!!)
                                com.vladislavmyasnikov.common.utils.Logger.debug(tag, "Database creation: DATA SAVING IS FINISHED; Class: $databaseClass")
                            }.start()
                        }
                    })
                    .build()
}