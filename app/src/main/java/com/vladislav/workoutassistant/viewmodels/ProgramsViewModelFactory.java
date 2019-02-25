package com.vladislav.workoutassistant.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProgramsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    public ProgramsViewModelFactory(@NonNull Application application, int categoryId) {
        mApplication = application;
        mCategoryId = categoryId;
    }

    @SuppressWarnings("UNCHECKED_CAST")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ProgramsViewModel(mApplication, mCategoryId);
    }

    @NonNull
    private final Application mApplication;
    private final int mCategoryId;
}
