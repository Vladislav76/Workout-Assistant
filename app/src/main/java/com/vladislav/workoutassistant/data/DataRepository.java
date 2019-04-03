package com.vladislav.workoutassistant.data;

import android.app.Application;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.db.LocalDatabase;
import com.vladislav.workoutassistant.data.db.entity.DiaryEntry;
import com.vladislav.workoutassistant.data.db.entity.Exercise;
import com.vladislav.workoutassistant.data.db.entity.Workout;
import com.vladislav.workoutassistant.data.models.NamedObject;
import com.vladislav.workoutassistant.data.models.WorkoutProgram;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class DataRepository {

    private static DataRepository sInstance;

    private Executor mExecutor;
    private LocalDatabase mDatabase;
    private MutableLiveData<DiaryEntry> mTempDiaryEntry;
    private MediatorLiveData<List<DiaryEntry>> mObservableEntries;
    private List<NamedObject> mCategories;
    private List<NamedObject> mIntensityLevels;
    private List<NamedObject> mMuscleGroups;

    private DataRepository(Application application) {
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

        mMuscleGroups = new ArrayList<>();
        names = application.getResources().getStringArray(R.array.muscle_groups);
        for (int i = 0; i < names.length; i++) {
            mMuscleGroups.add(new NamedObject(i, names[i]));
        }

        mTempDiaryEntry = new MutableLiveData<>();
        mTempDiaryEntry.setValue(new DiaryEntry());
        mExecutor = Executors.newSingleThreadExecutor();
    }

    public static DataRepository getInstance(Application application) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(application);
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

    public List<NamedObject> loadMuscleGroups() {
        return mMuscleGroups;
    }

    public LiveData<List<Workout>> loadWorkouts(int categoryId) {
        return mDatabase.workoutDao().loadWorkouts(categoryId);
    }

    public LiveData<Exercise> loadExercise(int exerciseId) {
        return mDatabase.exerciseDao().loadExercise(exerciseId);
    }

    public LiveData<List<Exercise>> loadExercises(List<Integer> exerciseIds) {
        return mDatabase.exerciseDao().loadExercises(exerciseIds);
    }

    public LiveData<List<Exercise>> loadExercises(int muscleGroupId) {
        return mDatabase.exerciseDao().loadExercises(muscleGroupId);
    }

    public LiveData<WorkoutProgram> loadWorkoutProgram(int workoutId) {
        return mDatabase.workoutDao().loadWorkoutProgram(workoutId);
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