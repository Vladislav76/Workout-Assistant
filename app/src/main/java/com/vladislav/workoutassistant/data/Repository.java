package com.vladislav.workoutassistant.data;

import android.app.Application;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.db.LocalDatabase;
import com.vladislav.workoutassistant.data.db.entity.DiaryEntry;
import com.vladislav.workoutassistant.data.db.entity.Workout;
import com.vladislav.workoutassistant.data.model.NamedObject;

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

    private static Repository sInstance;

    private Executor mExecutor;
    private LocalDatabase mDatabase;
    private MutableLiveData<DiaryEntry> mTempDiaryEntry;
    private MediatorLiveData<List<DiaryEntry>> mObservableEntries;
    private List<NamedObject> mCategories;
    private List<NamedObject> mIntensityLevels;

    private Repository(Application application) {
        mDatabase = LocalDatabase.getInstance(application);
        mObservableEntries = new MediatorLiveData<>();

        mObservableEntries.addSource(mDatabase.diaryDao().loadAllEntries(), new Observer<List<DiaryEntry>>() {
            @Override
            public void onChanged(List<DiaryEntry> diaryEntryEntities) {
                if (mDatabase.getDatabaseCreated().getValue() != null) {
                    mObservableEntries.postValue(diaryEntryEntities);
                }
            }
        });

        mCategories = new ArrayList<>();
        String[] names = application.getResources().getStringArray(R.array.categories);
        for (int i = 0; i < names.length; i++) {
            mCategories.add(new NamedObject(i, names[i]));
        }

        mIntensityLevels = new ArrayList<>();
        names = application.getResources().getStringArray(R.array.intensity_levels);
        for (int i = 0; i < names.length; i++) {
            mIntensityLevels.add(new NamedObject(i, names[i]));
        }

        mTempDiaryEntry = new MutableLiveData<>();
        mTempDiaryEntry.setValue(new DiaryEntry());
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

    public List<NamedObject> loadCategories() {
        return mCategories;
    }

    public List<NamedObject> loadIntensityLevels() {
        return mIntensityLevels;
    }

    public LiveData<List<Workout>> loadWorkouts(int categoryId) {
        return mDatabase.workoutDao().loadWorkouts(categoryId);
    }

    public LiveData<List<DiaryEntry>> loadDiaryEntries() {
        return mObservableEntries;
    }

    public LiveData<DiaryEntry> loadDiaryEntry(int diaryEntryId) {
        return mDatabase.diaryDao().loadEntry(diaryEntryId);
    }

    public LiveData<DiaryEntry> getNewEntry() {
        DiaryEntry entry = mTempDiaryEntry.getValue();
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

    public void insertNewEntry(final DiaryEntry diaryEntry) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatabase.diaryDao().insertEntry(diaryEntry);
            }
        });
    }

    public void updateEntry(final DiaryEntry diaryEntry) {
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
}

//    public LiveData<Program> getProgramById(int programId) {
//        return mDatabase.programDao().loadProgramById(programId);
//    }
//
//    public LiveData<List<Set>> getSetsById(int programId) {
//        LiveData<List<Set>> liveData = new MutableLiveData<>();
//        ((MutableLiveData<List<Set>>) liveData).postValue();
//        List<Set> sets = mDatabase.setDao().loadSetsByProgramId(programId);
//        for (Set set : sets) {
//            set.exercises = mDatabase.setAndExerciseMatchingDao().getExercisesBySetId(set.id);
//        }
//    }