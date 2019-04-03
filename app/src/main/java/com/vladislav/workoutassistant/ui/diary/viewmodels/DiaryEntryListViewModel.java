package com.vladislav.workoutassistant.ui.diary.viewmodels;

import android.app.Application;

import com.vladislav.workoutassistant.data.DataRepository;
import com.vladislav.workoutassistant.data.db.entity.DiaryEntry;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class DiaryEntryListViewModel extends AndroidViewModel {

    private DataRepository mDataRepository;
    private MediatorLiveData<List<DiaryEntry>> mEntryList = new MediatorLiveData<>();

    public DiaryEntryListViewModel(Application application) {
        super(application);

        mDataRepository = DataRepository.getInstance(application);
        mEntryList.addSource(mDataRepository.loadDiaryEntries(), new Observer<List<DiaryEntry>>() {
            @Override
            public void onChanged(List<DiaryEntry> diaryEntryEntities) {
                mEntryList.setValue(diaryEntryEntities);
            }
        });
    }

    public LiveData<List<DiaryEntry>> getEntries() {
        return mEntryList;
    }

    public void deleteEntriesById(List<Integer> ids) {
        mDataRepository.deleteEntriesById(ids);
    }
}
