package com.vladislav.workoutassistant.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DiaryEntryViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    public DiaryEntryViewModelFactory(@NonNull Application application, int diaryEntryId) {
        mApplication = application;
        mDiaryEntryId = diaryEntryId;
    }

    @SuppressWarnings("UNCHECKED_CAST")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new DiaryEntryViewModel(mApplication, mDiaryEntryId);
    }

    @NonNull
    private final Application mApplication;
    private final int mDiaryEntryId;
}
