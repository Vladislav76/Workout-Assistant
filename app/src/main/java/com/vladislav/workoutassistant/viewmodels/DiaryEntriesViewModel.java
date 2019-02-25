package com.vladislav.workoutassistant.viewmodels;

import android.app.Application;

import com.vladislav.workoutassistant.data.Repository;
import com.vladislav.workoutassistant.data.db.entity.DiaryEntryEntity;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class DiaryEntriesViewModel extends AndroidViewModel {

    public DiaryEntriesViewModel(Application application) {
        super(application);

        mRepository = Repository.getInstance(application);
//        mEntryList.setValue(null);
        mEntryList.addSource(mRepository.getAllEntries(), new Observer<List<DiaryEntryEntity>>() {
            @Override
            public void onChanged(List<DiaryEntryEntity> diaryEntryEntities) {
                mEntryList.setValue(diaryEntryEntities);
            }
        });
    }

    public LiveData<List<DiaryEntryEntity>> getEntries() {
        return mEntryList;
    }

    public void deleteEntriesById(List<Integer> ids) {
        mRepository.deleteEntriesById(ids);
    }

    private Repository mRepository;
    private MediatorLiveData<List<DiaryEntryEntity>> mEntryList = new MediatorLiveData<>();
}
