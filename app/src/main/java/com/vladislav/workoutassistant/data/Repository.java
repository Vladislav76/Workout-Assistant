package com.vladislav.workoutassistant.data;

import android.app.Application;

import com.vladislav.workoutassistant.data.db.LocalDatabase;
import com.vladislav.workoutassistant.data.db.entity.DiaryEntryEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class Repository {

    private Repository(Application application) {
        mDatabase = LocalDatabase.getInstance(application);
        mObservableEntries = new MediatorLiveData<>();

        mObservableEntries.addSource(mDatabase.diaryDao().loadAllEntries(), new Observer<List<DiaryEntryEntity>>() {
            @Override
            public void onChanged(List<DiaryEntryEntity> diaryEntryEntities) {
                if (mDatabase.getDatabaseCreated().getValue() != null) {
                    mObservableEntries.postValue(diaryEntryEntities);
                }
            }
        });

        mTempDiaryEntry = new MutableLiveData<>();
        mTempDiaryEntry.setValue(new DiaryEntryEntity());
        mExecutor = Executors.newSingleThreadExecutor();
    }

    public static Repository getInstance(Application application) {
        if (sInstance == null) {
            synchronized (Repository.class) {
                if (sInstance == null) {
                    sInstance = new Repository(application);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<DiaryEntryEntity>> getAllEntries() {
        return mObservableEntries;
    }

    public LiveData<DiaryEntryEntity> getEntry(int diaryEntryId) {
        return mDatabase.diaryDao().loadEntry(diaryEntryId);
    }

    public LiveData<DiaryEntryEntity> getNewEntry() {
        DiaryEntryEntity entry = mTempDiaryEntry.getValue();
        entry.setDate(new Date());
        entry.setStartTime(null);
        entry.setFinishTime(null);
        entry.setDuration(null);
        entry.setDefaultTitleChecked(false);
        entry.setTitle(null);
        if (entry.getMuscleGroupsIds().size() > 0) {
            entry.setMuscleGroupsIds(new ArrayList<Integer>());
        }
        return mTempDiaryEntry;
    }

    public void insertNewEntry(final DiaryEntryEntity diaryEntry) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.diaryDao().insertEntry(diaryEntry);
            }
        });
    }

    public void updateEntry(final DiaryEntryEntity diaryEntry) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.diaryDao().updateEntry(diaryEntry);
            }
        });
    }

    public void deleteEntriesById(final List<Integer> ids) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.diaryDao().deleteEntriesById(ids);
            }
        });
    }

    private Executor mExecutor;
    private final LocalDatabase mDatabase;
    private MutableLiveData<DiaryEntryEntity> mTempDiaryEntry;
    private MediatorLiveData<List<DiaryEntryEntity>> mObservableEntries;

    private static Repository sInstance;
}
