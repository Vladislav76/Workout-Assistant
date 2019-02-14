package com.vladislav.workoutassistant.data.db;

import com.vladislav.workoutassistant.data.db.entity.DiaryEntryEntity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DataGenerator {

    public static List<DiaryEntryEntity> generateEntries() {
        List<DiaryEntryEntity> entries = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Calendar calendar = new GregorianCalendar(0, 0, 0, 0, 0);
            Time time = new Time(calendar.getTime().getTime());
            DiaryEntryEntity entry = new DiaryEntryEntity(new Date(), time, time, "Workout №" + i);
            entries.add(entry);
        }
        return entries;
    }
}
