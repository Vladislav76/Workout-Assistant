package com.vladislav.workoutassistant.viewmodel;

import com.vladislav.workoutassistant.data.DiaryEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class DiaryEntryViewModel extends BaseObservable {

    private DiaryEntry mDiaryEntry;
    private SimpleDateFormat timeFormatter;
    private DateFormat dateFormatter;

    public DiaryEntryViewModel() {
        dateFormatter = DateFormat.getDateInstance(DateFormat.FULL);
        timeFormatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
    }

    @Bindable
    public String getId() {
        if (mDiaryEntry != null) {
            return Integer.toString(mDiaryEntry.getId());
        }
        return null;
    }

    @Bindable
    public String getTitle() {
        if (mDiaryEntry != null) {
            return mDiaryEntry.getTitle();
        }
        return null;
    }

    @Bindable
    public String getDate() {
        if (mDiaryEntry != null) {
            return dateFormatter.format(mDiaryEntry.getDate());
        }
        return null;
    }

    @Bindable
    public String getStartTime() {
        if (mDiaryEntry != null) {
            return timeFormatter.format(mDiaryEntry.getStartTime());
        }
        return null;
    }

    @Bindable
    public String getFinishTime() {
        if (mDiaryEntry != null) {
            return timeFormatter.format(mDiaryEntry.getFinishTime());
        }
        return null;
    }

    @Bindable
    public String getDuration() {
        if (mDiaryEntry != null) {
            return Integer.toString(mDiaryEntry.getDuration());
        }
        return null;
    }

    public DiaryEntry getDiaryEntry() {
        return mDiaryEntry;
    }

    public void setDiaryEntry(DiaryEntry diaryEntry) {
        mDiaryEntry = diaryEntry;
        notifyChange();
    }

    public void setDate(Date date) {
        mDiaryEntry.setDate(date);
        notifyChange();
    }

    public void setStartTime(Date time) {
        mDiaryEntry.setStartTime(time);
        notifyChange();
    }

    public void setFinishTime(Date time) {
        mDiaryEntry.setFinishTime(time);
        notifyChange();
    }
}
