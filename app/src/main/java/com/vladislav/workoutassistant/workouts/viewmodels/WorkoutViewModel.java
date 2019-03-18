package com.vladislav.workoutassistant.workouts.viewmodels;

import android.app.Application;

import com.vladislav.workoutassistant.data.Repository;
import com.vladislav.workoutassistant.data.db.entity.Exercise;
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
    private MediatorLiveData<WorkoutProgram> mWorkoutProgram = new MediatorLiveData<>();

    public WorkoutViewModel(Application application) {
        super(application);
        mRepository = Repository.getInstance(application);
    }

    public void init(final int workoutId) {
        mWorkoutProgram.addSource(mRepository.loadWorkoutProgram(workoutId), new Observer<WorkoutProgram>() {
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
                    mWorkoutProgram.addSource(mRepository.loadExercises(exerciseIds), new Observer<List<Exercise>>() {   //TODO: how to work in background?
                        @Override
                        public void onChanged(List<Exercise> exercises) {
                            if (exercises != null) {
                                for (WorkoutSet workoutSet : workoutSets) {
                                    addExerciseDataToSet(workoutSet.getWorkoutExercises(), exercises);
                                }
                                mWorkoutProgram.postValue(program);
                            }
                        }
                    });
                }
            }
        });
    }

    public LiveData<WorkoutProgram> getWorkoutProgram() {
        return mWorkoutProgram;
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
