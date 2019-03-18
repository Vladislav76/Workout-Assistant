package com.vladislav.workoutassistant.workouts.viewmodels;

import android.app.Application;

import com.vladislav.workoutassistant.data.Repository;
import com.vladislav.workoutassistant.data.db.entity.Workout;
import com.vladislav.workoutassistant.data.model.NamedObject;
import com.vladislav.workoutassistant.data.model.WorkoutGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class WorkoutGroupListViewModel extends AndroidViewModel {

    private Repository mRepository;
    private List<NamedObject> mIntensityLevels;
    private MediatorLiveData<List<WorkoutGroup>> mWorkoutGroups = new MediatorLiveData<>();

    public WorkoutGroupListViewModel(Application application) {
        super(application);
        mRepository = Repository.getInstance(application);
        mIntensityLevels = mRepository.loadIntensityLevels();
    }

    public void init(int categoryId) {
        mWorkoutGroups.addSource(mRepository.loadWorkouts(categoryId), new Observer<List<Workout>>() {
            @Override
            public void onChanged(List<Workout> workouts) {
                mWorkoutGroups.setValue(mergeIntoGroups(workouts));
            }
        });
    }

    public LiveData<List<WorkoutGroup>> getWorkoutGroups() {
        return mWorkoutGroups;
    }

    private List<WorkoutGroup> mergeIntoGroups(List<Workout> workouts) {
        List<WorkoutGroup> groups = new ArrayList<>();
        if (workouts != null && workouts.size() > 0) {
            int startPosition = 0;
            int currentGroupId = workouts.get(0).getIntensityLevelId();  //TODO: don't chain to this method, use groupId
            WorkoutGroup group = new WorkoutGroup(mIntensityLevels.get(currentGroupId));

            for (int i = 1; i < workouts.size(); i++) {
                Workout workout = workouts.get(i);
                if (workout.getIntensityLevelId() != currentGroupId) {
                    group.setWorkouts(workouts.subList(startPosition, i));
                    groups.add(group);
                    startPosition = i;
                    currentGroupId = workouts.get(i).getIntensityLevelId();
                    group = new WorkoutGroup(mIntensityLevels.get(currentGroupId));
                }
            }

            group.setWorkouts(workouts.subList(startPosition, workouts.size()));
            groups.add(group);
        }
        return groups;
    }
}
