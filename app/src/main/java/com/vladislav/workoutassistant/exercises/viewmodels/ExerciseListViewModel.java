package com.vladislav.workoutassistant.exercises.viewmodels;

import android.app.Application;
import android.util.SparseIntArray;

import com.vladislav.workoutassistant.data.Repository;
import com.vladislav.workoutassistant.data.db.entity.Exercise;
import com.vladislav.workoutassistant.data.model.NamedObject;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class ExerciseListViewModel extends AndroidViewModel {

    private Repository mRepository;
    private List<NamedObject> mMuscleGroups;
    private SparseIntArray mSelectedExercises;
    private MediatorLiveData<List<Exercise>> mExercises = new MediatorLiveData<>();

    public ExerciseListViewModel(Application application) {
        super(application);
        mRepository = Repository.getInstance(application);
        mMuscleGroups = mRepository.loadMuscleGroups();
        mSelectedExercises = new SparseIntArray();
    }

    public void init(int muscleGroupId) {
        mExercises.addSource(mRepository.loadExercises(muscleGroupId), new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                mExercises.postValue(exercises);
            }
        });
    }

    public List<NamedObject> getMuscleGroups() {
        return mMuscleGroups;
    }

    public LiveData<List<Exercise>> getExercises() {
        return mExercises;
    }

    public SparseIntArray getSelectedExercises() {
        return mSelectedExercises;
    }

    public void setSelected(int exerciseId, int exerciseCount) {
        mSelectedExercises.put(exerciseId, exerciseCount);
    }

    public void removeSelected(int exerciseId) {
        mSelectedExercises.delete(exerciseId);
    }

    public int getExerciseCount(int exerciseId, int defaultValue) {
        return mSelectedExercises.get(exerciseId, defaultValue);
    }
}
