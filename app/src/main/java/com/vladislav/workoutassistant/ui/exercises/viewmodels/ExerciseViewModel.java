package com.vladislav.workoutassistant.ui.exercises.viewmodels;

import android.app.Application;

import com.vladislav.workoutassistant.data.DataRepository;
import com.vladislav.workoutassistant.data.db.entity.Exercise;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class ExerciseViewModel extends AndroidViewModel {

    private DataRepository mDataRepository;
    private MediatorLiveData<Exercise> mExercise = new MediatorLiveData<>();

    public ExerciseViewModel(Application application) {
        super(application);
        mDataRepository = DataRepository.Companion.getInstance(application);
    }

    public void init(final int exerciseId) {
        mExercise.addSource(mDataRepository.loadExercise(exerciseId), new Observer<Exercise>() {
            @Override
            public void onChanged(Exercise exercise) {
                mExercise.setValue(exercise);
            }
        });
    }

    public LiveData<Exercise> getExercise() {
        return mExercise;
    }
}
