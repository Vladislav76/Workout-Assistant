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
            DiaryEntry entry = new DiaryEntry(i, date, time, time, "Workout №" + i);
            mEntries.add(entry);
        }
    }

    public List<DiaryEntry> getEntries() {
        return mEntries;
    }

    public DiaryEntry getEntryById(int id) {
        for (DiaryEntry entry : mEntries) {
            if (entry.getId() == id) {
                return entry;
            }
        }
        return null;
    }

    public static Diary getDiary() {
        if (sDiary == null) {
            sDiary = new Diary();
        }
        return sDiary;
    }

    private List<DiaryEntry> mEntries;

    private static Diary sDiary;
}
