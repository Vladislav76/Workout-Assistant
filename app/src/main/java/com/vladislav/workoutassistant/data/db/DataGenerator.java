package com.vladislav.workoutassistant.data.db;

import com.vladislav.workoutassistant.data.db.entity.DiaryEntryEntity;
import com.vladislav.workoutassistant.data.db.entity.ExerciseEntity;
import com.vladislav.workoutassistant.data.db.entity.ProgramCategoryEntity;
import com.vladislav.workoutassistant.data.db.entity.SetAndExerciseMatchingEntity;
import com.vladislav.workoutassistant.data.db.entity.ProgramEntity;
import com.vladislav.workoutassistant.data.db.entity.SetEntity;

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

    public static List<ProgramCategoryEntity> generateCategories() {
        List<ProgramCategoryEntity> categories = new ArrayList<>();
        categories.add(new ProgramCategoryEntity("Beginner"));
        categories.add(new ProgramCategoryEntity("Intermediate"));
        categories.add(new ProgramCategoryEntity("Advanced"));
        categories.add(new ProgramCategoryEntity("Custom"));
        return categories;
    }

    public static ProgramEntity generateProgram() {
        ProgramEntity program = new ProgramEntity("New program", 1);
        return program;
    }

    public static List<SetEntity> generateSets() {
        List<SetEntity> sets = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            SetEntity set = new SetEntity(i * 10, 1);
            sets.add(set);
        }
        return sets;
    }

    public static List<ExerciseEntity> generateExercises() {
        List<ExerciseEntity> exercises = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ExerciseEntity exercise = new ExerciseEntity("Exercise №" + i, "Description №" + i, i / 2);
            exercises.add(exercise);
        }
        return exercises;
    }

    public static List<SetAndExerciseMatchingEntity> generateSetAndExerciseMatching() {
        List<SetAndExerciseMatchingEntity> matching = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            SetAndExerciseMatchingEntity entry = new SetAndExerciseMatchingEntity(i / 3 + 1, i + 1, (i + 1) * 5);
            matching.add(entry);
        }
        return matching;
    }
}
