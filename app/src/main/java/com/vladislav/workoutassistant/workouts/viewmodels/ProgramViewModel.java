package com.vladislav.workoutassistant.workouts.viewmodels;

import android.app.Application;

import com.vladislav.workoutassistant.data.Repository;
import com.vladislav.workoutassistant.data.model.Program;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ProgramViewModel extends AndroidViewModel {

    public ProgramViewModel(Application application, int programId) {
        super(application);
        mRepository = Repository.getInstance(application);
        mProgram = mRepository.getProgramById(programId);
    }

    public LiveData<Program> getProgram() {
        return mProgram;
    }

    private final Repository mRepository;
    private final LiveData<Program> mProgram;
}
