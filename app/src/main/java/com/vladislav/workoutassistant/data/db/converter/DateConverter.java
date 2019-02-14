package com.vladislav.workoutassistant.data.db.converter;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import androidx.room.TypeConverter;

public class DateConverter {

    @TypeConverter
    public static Date timestampToDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Time timestampToTime(Long value) {
        return value == null ? null : new Time(value);
    }

    @TypeConverter
    public static Long timeToTimestamp(Time time) {
        return time == null ? null : time.getTime();
    }

    @TypeConverter
    public static ArrayList<Integer> stringToIntegerArrayList(String data) {
        if (data == null || data.length() == 2) {
            return new ArrayList<>();
        }
        String[] strings = data.substring(1, data.length() - 1).split(", ");
        ArrayList<Integer> values = new ArrayList<>(strings.length);
        for (String s : strings) {
            System.out.print(s + "|");
        }
        System.out.println();

        for (String s : strings) {
            values.add(Integer.parseInt(s));
        }
        return values;
    }

    @TypeConverter
    public static String integerArrayListToString(ArrayList<Integer> list) {
        if (list == null) {
            return null;
        }
        System.out.println(list.toString());
        return list.toString();
    }

    public static String dateToString(Date date) {
        return date == null ? null : DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault()).format(date);
    }

    public static String timeToString(Date time) {
        return time == null ? null : DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault()).format(time);
    }

    public static String durationToString(Date duration) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GTM"));
        return duration == null ? null : simpleDateFormat.format(duration);
    }
}
