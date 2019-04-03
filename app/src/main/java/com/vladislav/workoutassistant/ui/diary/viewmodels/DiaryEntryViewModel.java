package com.vladislav.workoutassistant.ui.diary.viewmodels;

import android.app.Application;

import com.vladislav.workoutassistant.data.DataRepository;
import com.vladislav.workoutassistant.data.db.entity.DiaryEntry;

import java.sql.Time;
import java.util.Date;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DiaryEntryViewModel extends AndroidViewModel {

    private final DataRepository mDataRepository;
    private final LiveData<DiaryEntry> mDiaryEntry;
    private ObservableField<DiaryEntry> entry = new ObservableField<>();

    public static final int NEW_DIARY_ENTRY_ID = -1;

    public DiaryEntryViewModel(Application application, int diaryEntryId) {
        super(application);
        mDataRepository = DataRepository.getInstance(application);
        if (diaryEntryId == NEW_DIARY_ENTRY_ID) {
            mDiaryEntry = mDataRepository.getNewEntry();
        }
        else {
            mDiaryEntry = mDataRepository.loadDiaryEntry(diaryEntryId);
        }
    }

    public DiaryEntryViewModel(Application application) {
        super(application);
        mDataRepository = DataRepository.getInstance(application);
        mDiaryEntry = null;
    }

    public LiveData<DiaryEntry> getDiaryEntry() {
        return mDiaryEntry;
    }

    public ObservableField<DiaryEntry> getEntry() {
        return entry;
    }

    public void setEntry(DiaryEntry entry) {
        this.entry.set(entry);
    }

    public void setTitle(String title) {
        entry.get().setTitle(title);
        entry.notifyChange();
    }

    public void setDefaultTitle(String title) {
        entry.get().setDefaultTitle(title);
        entry.notifyChange();
    }

    public void setDate(Date date) {
        entry.get().setDate(date);
        entry.notifyChange();
    }

    public void setStartTime(Time time) {
        entry.get().setStartTime(time);
        entry.notifyChange();
    }

    public void setFinishTime(Time time) {
        entry.get().setFinishTime(time);
        entry.notifyChange();
    }

    public void setDuration(Date duration) {
        entry.get().setDuration(duration);
        entry.notifyChange();
    }

    public void setDefaultTitleChecked(boolean isChecked) {
        entry.get().setDefaultTitleChecked(isChecked);
        entry.notifyChange();
    }

    public void setSelected(boolean isSelected) {
        entry.get().setSelected(isSelected);
        entry.notifyChange();
    }

    public void updateEntry() {
        mDataRepository.updateEntry(entry.get());
    }

    public void insertEntry() {
        mDataRepository.insertNewEntry(entry.get());
    }
}
