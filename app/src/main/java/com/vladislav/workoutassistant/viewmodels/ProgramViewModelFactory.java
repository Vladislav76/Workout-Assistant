package com.vladislav.workoutassistant.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProgramViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    public ProgramViewModelFactory(@NonNull Application application, int programId) {
        mApplication = application;
        mProgramId = programId;
    }

    @SuppressWarnings("UNCHECKED_CAST")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ProgramViewModel(mApplication, mProgramId);
    }

    @NonNull
    private final Application mApplication;
    private final int mProgramId;
}
