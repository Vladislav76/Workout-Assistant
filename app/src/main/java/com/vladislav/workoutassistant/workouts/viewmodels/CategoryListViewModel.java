package com.vladislav.workoutassistant.workouts.viewmodels;

import android.app.Application;

import com.vladislav.workoutassistant.data.Repository;
import com.vladislav.workoutassistant.data.model.NamedObject;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;

public class CategoryListViewModel extends AndroidViewModel {

    private List<NamedObject> mCategories;

    public CategoryListViewModel(Application application) {
        super(application);
        Repository repository = Repository.getInstance(application);
        mCategories = repository.loadCategories();
    }

    public List<NamedObject> getCategories() {
        return mCategories;
    }
}
