package com.vladislav.workoutassistant.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vladislav.workoutassistant.data.db.converter.DateTimeConverter
import com.vladislav.workoutassistant.data.db.converter.ListConverter
import com.vladislav.workoutassistant.data.db.dao.*
import com.vladislav.workoutassistant.data.db.entity.*
import com.vladislav.workoutassistant.data.db.entity.Set

@Database(entities = [DiaryEntry::class, Exercise::class, Workout::class, Set::class, SetVsExerciseMatching::class], version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class, ListConverter::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun diaryDao(): DiaryDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun setDao(): SetDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun setAndExerciseMatchingDao(): SetVsExerciseMatchingDao

    companion object {

        private const val sDatabaseName = "My local database"
        private var sInstance: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            return sInstance ?: synchronized(this) {
                sInstance ?: buildDatabase(context).also { sInstance = it }
            }
        }

        private fun buildDatabase(context: Context): LocalDatabase =
                Room.databaseBuilder(context, LocalDatabase::class.java, sDatabaseName)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            Thread(Runnable {
                                insertData(getInstance(context))
                            }).start()
                        }
                    }).build()

        private fun insertData(database: LocalDatabase) {
            database.exerciseDao().insertExercises(DataGenerator.generateExercises())

            val workouts = DataGenerator.generateWorkouts()
            database.workoutDao().insertWorkouts(workouts)
            database.diaryDao().insertEntries(DataGenerator.generateEntries(workouts.size))

            val sets = DataGenerator.generateSets(workouts.size)
            database.setDao().insertSets(sets)
            database.setAndExerciseMatchingDao().insertMatching(DataGenerator.generateSetAndExerciseMatching(sets.size))
        }
    }
}
