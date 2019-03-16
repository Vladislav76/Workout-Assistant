package com.vladislav.workoutassistant.data.db;

import com.vladislav.workoutassistant.data.db.entity.DiaryEntry;
import com.vladislav.workoutassistant.data.db.entity.Exercise;
import com.vladislav.workoutassistant.data.db.entity.Set;
import com.vladislav.workoutassistant.data.db.entity.SetVsExerciseMatching;
import com.vladislav.workoutassistant.data.db.entity.Workout;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DataGenerator {

    public static List<DiaryEntry> generateEntries() {
        List<DiaryEntry> entries = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            Calendar calendar = new GregorianCalendar(0, 0, 0, 0, 0);
            Time time = new Time(calendar.getTime().getTime());
            DiaryEntry entry = new DiaryEntry(new Date(), time, time, "Workout №" + i);
            entries.add(entry);
        }
        return entries;
    }

    public static List<Workout> generateWorkouts() {
        List<Workout> workouts = new ArrayList<>();
        for (int k = 0; k <= 10; k++) {
            for (int l = 0; l < 3; l++) {
                for (int i = 1; i < 10; i++) {
                    Workout workout = new Workout("Workout " + i, k, l);
                    workouts.add(workout);
                }
            }
        }
        return workouts;
    }

    public static List<Set> generateSets() {
        List<Set> sets = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Set set = new Set(i * 10, 1);
            sets.add(set);
        }
        return sets;
    }

    public static List<Exercise> generateExercises() {
        List<Exercise> exercises = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Exercise exercise = new Exercise("Exercise №" + i, "Description №" + i, i / 2);
            exercises.add(exercise);
        }
        return exercises;
    }

    public static List<SetVsExerciseMatching> generateSetAndExerciseMatching() {
        List<SetVsExerciseMatching> matching = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            SetVsExerciseMatching entry = new SetVsExerciseMatching(i / 3 + 1, i + 1, (i + 1) * 5);
            matching.add(entry);
        }
        return matching;
    }
}
