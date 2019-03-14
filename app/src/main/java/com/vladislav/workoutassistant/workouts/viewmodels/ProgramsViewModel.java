package com.vladislav.workoutassistant.workouts.viewmodels;

import android.app.Application;

import com.vladislav.workoutassistant.data.Repository;
import com.vladislav.workoutassistant.data.db.entity.ProgramEntity;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class ProgramsViewModel extends AndroidViewModel {

    public ProgramsViewModel(Application application, int categoryId) {
        super(application);

        mRepository = Repository.getInstance(application);
        mProgramList.addSource(mRepository.getProgramsByCategory(categoryId), new Observer<List<ProgramEntity>>() {
            @Override
            public void onChanged(List<ProgramEntity> programs) {
                mProgramList.setValue(programs);
            }
        });
    }

    public LiveData<List<ProgramEntity>> getPrograms() {
        return mProgramList;
    }

    private Repository mRepository;
    private MediatorLiveData<List<ProgramEntity>> mProgramList = new MediatorLiveData<>();
}
