package com.vladislav.workoutassistant.data.db;

import android.content.Context;

import com.vladislav.workoutassistant.data.db.converter.DateConverter;
import com.vladislav.workoutassistant.data.db.dao.DiaryDao;
import com.vladislav.workoutassistant.data.db.dao.ExerciseDao;
import com.vladislav.workoutassistant.data.db.dao.ProgramCategoryDao;
import com.vladislav.workoutassistant.data.db.dao.ProgramDao;
import com.vladislav.workoutassistant.data.db.dao.SetAndExerciseMatchingDao;
import com.vladislav.workoutassistant.data.db.dao.SetDao;
import com.vladislav.workoutassistant.data.db.entity.DiaryEntryEntity;
import com.vladislav.workoutassistant.data.db.entity.ExerciseEntity;
import com.vladislav.workoutassistant.data.db.entity.ProgramCategoryEntity;
import com.vladislav.workoutassistant.data.db.entity.ProgramEntity;
import com.vladislav.workoutassistant.data.db.entity.SetAndExerciseMatchingEntity;
import com.vladislav.workoutassistant.data.db.entity.SetEntity;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {DiaryEntryEntity.class, ExerciseEntity.class, ProgramEntity.class,
        SetEntity.class, SetAndExerciseMatchingEntity.class, ProgramCategoryEntity.class},
        version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class LocalDatabase extends RoomDatabase {

    public abstract DiaryDao diaryDao();
    public abstract ExerciseDao exerciseDao();
    public abstract SetDao setDao();
    public abstract ProgramCategoryDao programCategoryDao();
    public abstract ProgramDao programDao();
    public abstract SetAndExerciseMatchingDao setAndExerciseMatchingDao();

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
        database.diaryDao().insertEntries(DataGenerator.generateEntries());
        database.exerciseDao().insertExercises(DataGenerator.generateExercises());
        database.programCategoryDao().insertCategories(DataGenerator.generateCategories());
        database.programDao().insertProgram(DataGenerator.generateProgram());
        database.setDao().insertSets(DataGenerator.generateSets());
        database.setAndExerciseMatchingDao().insertMatching(DataGenerator.generateSetAndExerciseMatching());
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

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    private static volatile LocalDatabase sInstance;
    private static final String DATABASE_NAME = "local_database";
}
