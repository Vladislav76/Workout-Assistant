package com.vladislav.workoutassistant.workouts.viewmodels;

import android.app.Application;

import com.vladislav.workoutassistant.data.Repository;
import com.vladislav.workoutassistant.data.db.entity.ProgramCategoryEntity;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class ProgramCategoriesViewModel extends AndroidViewModel {

    public ProgramCategoriesViewModel(Application application) {
        super(application);

        mRepository = Repository.getInstance(application);
        mProgramCategoryList.addSource(mRepository.getProgramCategories(), new Observer<List<ProgramCategoryEntity>>() {
            @Override
            public void onChanged(List<ProgramCategoryEntity> categories) {
                mProgramCategoryList.setValue(categories);
            }
        });
    }

    public LiveData<List<ProgramCategoryEntity>> getCategories() {
        return mProgramCategoryList;
    }

    private Repository mRepository;
    private MediatorLiveData<List<ProgramCategoryEntity>> mProgramCategoryList = new MediatorLiveData<>();
}
