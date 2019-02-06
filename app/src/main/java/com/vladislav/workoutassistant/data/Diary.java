package com.vladislav.workoutassistant.data;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Diary {

    private Diary() {
        mEntries = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Date date = new Date();
            Calendar calendar = new GregorianCalendar(0, 0, 0, 0, 0);
            Time time = new Time(calendar.getTime().getTime());
            DiaryEntry entry = new DiaryEntry(date, time, time, "Workout №" + i);
            mEntries.add(entry);
        }
    }

    public List<DiaryEntry> getEntries() {
        return mEntries;
    }

    public DiaryEntry getTempDiaryEntry() {
        return mTempDiaryEntry;
    }

    public DiaryEntry getEntryById(int id) {
        for (DiaryEntry entry : mEntries) {
            if (entry.getId() == id) {
                return entry;
            }
        }
        return null;
    }

    public boolean addEntry(DiaryEntry entry) {
        entry.setId(Diary.getNextDiaryEntryIdAndIncrement());
        return mEntries.add(entry);
    }

    public boolean updateEntry(DiaryEntry updatedEntry) {
        DiaryEntry entry = getEntryById(updatedEntry.getId());
        if (entry != null) {
            entry.setDate(updatedEntry.getDate());
            entry.setStartTime(updatedEntry.getStartTime());
            entry.setFinishTime(updatedEntry.getFinishTime());
            entry.setDuration(updatedEntry.getDuration());
            entry.setMuscleGroupsIds(updatedEntry.getMuscleGroupsIds());
            entry.setTitle(updatedEntry.getTitle());
            return true;
        }
        return false;
    }

    public void setTempDiaryEntry(int id) {
        DiaryEntry entry = getEntryById(id);
        if (entry != null) {
            try {
                mTempDiaryEntry = (DiaryEntry) entry.clone();
            }
            catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        else if (id == NEW_TEMP_DIARY_ENTRY) {
            mTempDiaryEntry = new DiaryEntry();
        }
        else {
            mTempDiaryEntry = null;
        }
    }

    public static Diary getDiary() {
        if (sDiary == null) {
            sDiary = new Diary();
        }
        return sDiary;
    }

    public static int getNextDiaryEntryIdAndIncrement() {
        return sNextDiaryEntryId++;
    }

    private List<DiaryEntry> mEntries;
    private DiaryEntry mTempDiaryEntry;

    private static Diary sDiary;
    private static int sNextDiaryEntryId = 0;

    public static final int NEW_TEMP_DIARY_ENTRY = -1;
    public static final int NULL_TEMP_DIARY_ENTRY = -2;
}
