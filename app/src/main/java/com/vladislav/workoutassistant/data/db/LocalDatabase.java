package com.vladislav.workoutassistant.data.db;

import android.content.Context;

import com.vladislav.workoutassistant.data.db.converter.DateConverter;
import com.vladislav.workoutassistant.data.db.dao.DiaryDao;
import com.vladislav.workoutassistant.data.db.dao.ExerciseDao;
import com.vladislav.workoutassistant.data.db.dao.SetVsExerciseMatchingDao;
import com.vladislav.workoutassistant.data.db.dao.SetDao;
import com.vladislav.workoutassistant.data.db.dao.WorkoutDao;
import com.vladislav.workoutassistant.data.db.entity.DiaryEntry;
import com.vladislav.workoutassistant.data.db.entity.Exercise;
import com.vladislav.workoutassistant.data.db.entity.SetVsExerciseMatching;
import com.vladislav.workoutassistant.data.db.entity.Set;
import com.vladislav.workoutassistant.data.db.entity.Workout;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {DiaryEntry.class, Exercise.class, Workout.class, Set.class, SetVsExerciseMatching.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class LocalDatabase extends RoomDatabase {

    private static LocalDatabase sInstance;
    private static final String DATABASE_NAME = "local_database";

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public abstract DiaryDao diaryDao();
    public abstract ExerciseDao exerciseDao();
    public abstract SetDao setDao();
    public abstract WorkoutDao workoutDao();
    public abstract SetVsExerciseMatchingDao setAndExerciseMatchingDao();

    public static LocalDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (LocalDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static LocalDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, LocalDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {

                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        new Thread(new Runnable() {
                            public void run() {
                                LocalDatabase database = LocalDatabase.getInstance(context);
                                insertData(database);
                                database.setDatabaseCreated();
                            }
                        }).start();
                    }
                })
                .build();
    }

    private static void insertData(final LocalDatabase database) {
        database.exerciseDao().insertExercises(DataGenerator.generateExercises());

        List<Workout> workouts = DataGenerator.generateWorkouts();
        database.workoutDao().insertWorkouts(workouts);
        database.diaryDao().insertEntries(DataGenerator.generateEntries(workouts.size()));

        List<Set> sets = DataGenerator.generateSets(workouts.size());
        database.setDao().insertSets(sets);
        database.setAndExerciseMatchingDao().insertMatching(DataGenerator.generateSetAndExerciseMatching(sets.size()));
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }
}
