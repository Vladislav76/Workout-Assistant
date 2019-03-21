package com.vladislav.workoutassistant.workouts.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.model.NamedObject;
import com.vladislav.workoutassistant.data.model.WorkoutExercise;
import com.vladislav.workoutassistant.data.model.WorkoutSet;
import com.vladislav.workoutassistant.core.callbacks.ItemClickCallback;
import com.vladislav.workoutassistant.databinding.ItemWorkoutExerciseBinding;
import com.vladislav.workoutassistant.databinding.ItemWorkoutSetBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class SetAndExerciseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SET_ITEM_VIEW_TYPE = 1;
    private static final int EXERCISE_ITEM_VIEW_TYPE = 2;

    private List<Object> mItems;
    private List<NamedObject> mMuscleGroups;
    private ItemClickCallback mCallback;

    public SetAndExerciseAdapter(ItemClickCallback callback, List<NamedObject> muscleGroups) {
        mCallback = callback;
        mMuscleGroups = muscleGroups;
    }

    public void setList(List<Object> items) {
        mItems = items;  //TODO: add DiffUtil
    }

    @Override
    public int getItemViewType(int position) {
        Object object = mItems.get(position);
        if (object instanceof WorkoutSet) {
            return SET_ITEM_VIEW_TYPE;
        }
        if (object instanceof WorkoutExercise) {
            return EXERCISE_ITEM_VIEW_TYPE;
        }
        return 0;
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case SET_ITEM_VIEW_TYPE:
                ItemWorkoutSetBinding setBinding = DataBindingUtil.inflate(inflater, R.layout.item_workout_set, parent, false);
                setBinding.getRoot().setTag(R.id.black_horizontal_divider, "");
                return new SetViewHolder(setBinding);
            case EXERCISE_ITEM_VIEW_TYPE:
                ItemWorkoutExerciseBinding exerciseBinding = DataBindingUtil.inflate(inflater, R.layout.item_workout_exercise, parent, false);
                exerciseBinding.setCallback(mCallback);
                return new ExerciseViewHolder(exerciseBinding);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SetViewHolder) {
            ((SetViewHolder) holder).bind((WorkoutSet) mItems.get(position));
        }
        else if (holder instanceof ExerciseViewHolder) {
            ((ExerciseViewHolder) holder).bind((WorkoutExercise) mItems.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    class SetViewHolder extends RecyclerView.ViewHolder {
        private ItemWorkoutSetBinding mBinding;

        SetViewHolder(ItemWorkoutSetBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(WorkoutSet set) {
            mBinding.setName("Set");
            mBinding.setReps(set.setInfo.getId());
        }
    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private ItemWorkoutExerciseBinding mBinding;

        ExerciseViewHolder(ItemWorkoutExerciseBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(WorkoutExercise exercise) {
            mBinding.setName(exercise.exerciseInfo.getName());
            mBinding.setReps(exercise.matchingInfo.getReps());
            mBinding.setMuscleGroup(mMuscleGroups.get(exercise.exerciseInfo.getMuscleGroup()).getName());
        }
    }
}
