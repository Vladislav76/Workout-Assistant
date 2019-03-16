package com.vladislav.workoutassistant.workouts.viewmodels;

import android.app.Application;

import com.vladislav.workoutassistant.data.Repository;
import com.vladislav.workoutassistant.data.model.NamedObject;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;

public class GeneralInfoViewModel extends AndroidViewModel {

    private List<NamedObject> mCategories;
    private List<NamedObject> mIntensityLevels;

    public GeneralInfoViewModel(Application application) {
        super(application);
        Repository repository = Repository.getInstance(application);
        mCategories = repository.loadCategories();
        mIntensityLevels = repository.loadIntensityLevels();
    }

    public List<NamedObject> getCategories() {
        return mCategories;
    }

    public List<NamedObject> getIntensityLevels() {
        return mIntensityLevels;
    }
}
