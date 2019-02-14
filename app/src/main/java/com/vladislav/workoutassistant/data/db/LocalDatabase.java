package com.vladislav.workoutassistant.data.db;

import android.content.Context;

import com.vladislav.workoutassistant.data.db.converter.DateConverter;
import com.vladislav.workoutassistant.data.db.dao.DiaryDao;
import com.vladislav.workoutassistant.data.db.entity.DiaryEntryEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = DiaryEntryEntity.class, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class LocalDatabase extends RoomDatabase {

    public abstract DiaryDao diaryDao();

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
                                List<DiaryEntryEntity> entries = DataGenerator.generateEntries();
                                insertData(database, entries);
                                database.setDatabaseCreated();
                            }
                        }).start();
                    }
                })
                .build();
    }

    private static void insertData(final LocalDatabase database, final List<DiaryEntryEntity> entries) {
        database.diaryDao().insertEntries(entries);
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
