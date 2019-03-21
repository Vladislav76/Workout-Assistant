package com.vladislav.workoutassistant.workouts.viewmodels;

import android.app.Application;

import com.vladislav.workoutassistant.data.Repository;
import com.vladislav.workoutassistant.data.db.entity.Exercise;
import com.vladislav.workoutassistant.data.model.NamedObject;
import com.vladislav.workoutassistant.data.model.WorkoutExercise;
import com.vladislav.workoutassistant.data.model.WorkoutProgram;
import com.vladislav.workoutassistant.data.model.WorkoutSet;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class WorkoutViewModel extends AndroidViewModel {

    private Repository mRepository;
    private WorkoutProgram mWorkoutProgram;
    private List<NamedObject> mMuscleGroups;
    private MediatorLiveData<List<Object>> mSetsAndExercises = new MediatorLiveData<>();

    public WorkoutViewModel(Application application) {
        super(application);
        mRepository = Repository.getInstance(application);
        mMuscleGroups = mRepository.loadMuscleGroups();
    }

    public void init(final int workoutId) {
        mSetsAndExercises.addSource(mRepository.loadWorkoutProgram(workoutId), new Observer<WorkoutProgram>() {
            @Override
            public void onChanged(final WorkoutProgram program) {
                final List<WorkoutSet> workoutSets = program.getWorkoutSets();
                if (workoutSets != null) {
                    ArrayList<Integer> exerciseIds = new ArrayList<>();
                    for (WorkoutSet set : workoutSets) {
                        for (WorkoutExercise workoutExercise : set.getWorkoutExercises()) {
                            exerciseIds.add(workoutExercise.matchingInfo.getExerciseId());
                        }
                    }
                    mSetsAndExercises.addSource(mRepository.loadExercises(exerciseIds), new Observer<List<Exercise>>() {   //TODO: how to work in background?
                        @Override
                        public void onChanged(List<Exercise> exercises) {
                            if (exercises != null) {
                                for (WorkoutSet workoutSet : workoutSets) {
                                    addExerciseDataToSet(workoutSet.getWorkoutExercises(), exercises);
                                }
                                mSetsAndExercises.postValue(workoutProgramToList(program));
                                mWorkoutProgram = program;
                            }
                        }
                    });
                }
            }
        });
    }

    public List<NamedObject> getMuscleGroups() {
        return mMuscleGroups;
    }

    public WorkoutProgram getWorkoutProgram() {
        return mWorkoutProgram;
    }

    public LiveData<List<Object>> getSetsAndExercisesList() {
        return mSetsAndExercises;
    }

    private List<Object> workoutProgramToList(WorkoutProgram program) {
        List<Object> list = new ArrayList<>();
        List<WorkoutSet> sets = program.getWorkoutSets();
        for (WorkoutSet set : sets) {
            list.add(set);
            list.addAll(set.getWorkoutExercises());
        }
        return list;
    }

    private void addExerciseDataToSet(List<WorkoutExercise> workoutExercises, List<Exercise> exercises) {
        for (WorkoutExercise workoutExercise : workoutExercises) {
            int keyId = workoutExercise.matchingInfo.getExerciseId();
            for (Exercise exercise : exercises) {
                if (exercise.getId() == keyId) {
                    workoutExercise.exerciseInfo = exercise;
                    break;
                }
            }
        }
    }
}
