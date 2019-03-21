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
import java.util.Random;

public class DataGenerator {

    private static final int EXERCISE_NUMBER = 100;
    private static final int CATEGORY_NUMBER = 6;
    private static final int INTENSITY_LEVEL_NUMBER = 3;
    private static final int MUSCLE_GROUP_NUMBER = 9;
    private static final int MINIMUM_WORKOUT_NUMBER_PER_GROUP = 10;
    private static final int MAXIMUM_WORKOUT_NUMBER_PER_GROUP = 20;
    private static final int MINIMUM_SET_NUMBER_PER_WORKOUT = 5;
    private static final int MAXIMUM_SET_NUMBER_PER_WORKOUT = 10;
    private static final int MINIMUM_EXERCISE_NUMBER_PER_SET = 3;
    private static final int MAXIMUM_EXERCISE_NUMBER_PER_SET = 7;

    public static List<DiaryEntry> generateEntries(int workoutNumber) {
        List<DiaryEntry> entries = new ArrayList<>();
        for (int i = 1; i <= workoutNumber; i++) {
            Calendar calendar = new GregorianCalendar(0, 0, 0, 0, 0);
            Time time = new Time(calendar.getTime().getTime());
            DiaryEntry entry = new DiaryEntry(new Date(), time, time, "Workout №" + i);
            entries.add(entry);
        }
        return entries;
    }

    public static List<Workout> generateWorkouts() {
        Random random = new Random();
        int currentWorkoutId = 1;
        List<Workout> workouts = new ArrayList<>();
        for (int categoryId = 0; categoryId < CATEGORY_NUMBER; categoryId++) {
            for (int levelId = 0; levelId < INTENSITY_LEVEL_NUMBER; levelId++) {
                for (int i = 0; i <= random.nextInt(MAXIMUM_WORKOUT_NUMBER_PER_GROUP - MINIMUM_WORKOUT_NUMBER_PER_GROUP + 1) + MINIMUM_WORKOUT_NUMBER_PER_GROUP; i++) {
                    Workout workout = new Workout("Workout " + currentWorkoutId, categoryId, levelId);
                    workouts.add(workout);
                    currentWorkoutId++;
                }
            }
        }
        return workouts;
    }

    public static List<Set> generateSets(int workoutNumber) {
        Random random = new Random();
        List<Set> sets = new ArrayList<>();
        for (int i = 1; i <= workoutNumber; i++) {
            for (int k = 0; k <= random.nextInt(MAXIMUM_SET_NUMBER_PER_WORKOUT - MINIMUM_SET_NUMBER_PER_WORKOUT + 1) + MINIMUM_SET_NUMBER_PER_WORKOUT; k++) {
                Set set = new Set(random.nextInt(50), i);
                sets.add(set);
            }
        }
        return sets;
    }

    public static List<Exercise> generateExercises() {
        Random random = new Random();
        List<Exercise> exercises = new ArrayList<>();
        for (int i = 1; i <= EXERCISE_NUMBER; i++) {
            Exercise exercise = new Exercise("Exercise " + i, "Description " + i, random.nextInt(MUSCLE_GROUP_NUMBER));
            exercises.add(exercise);
        }
        return exercises;
    }

    public static List<SetVsExerciseMatching> generateSetAndExerciseMatching(int setNumber) {
        Random random = new Random();
        List<SetVsExerciseMatching> matching = new ArrayList<>();
        for (int i = 1; i <= setNumber; i++) {
            for (int k = 0; k <= random.nextInt(MAXIMUM_EXERCISE_NUMBER_PER_SET - MINIMUM_EXERCISE_NUMBER_PER_SET + 1) + MINIMUM_EXERCISE_NUMBER_PER_SET; k++) {
                SetVsExerciseMatching entry = new SetVsExerciseMatching(i, random.nextInt(EXERCISE_NUMBER) + 1, random.nextInt(999));
                matching.add(entry);
            }
        }
        return matching;
    }
}
