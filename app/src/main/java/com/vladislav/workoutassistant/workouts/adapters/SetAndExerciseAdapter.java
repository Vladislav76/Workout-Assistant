package com.vladislav.workoutassistant.workouts.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.model.Exercise;
import com.vladislav.workoutassistant.data.model.RepeatableObject;
import com.vladislav.workoutassistant.data.model.Set;
import com.vladislav.workoutassistant.databinding.ItemExerciseBinding;
import com.vladislav.workoutassistant.databinding.ItemSetBinding;
import com.vladislav.workoutassistant.core.callbacks.ItemClickCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class SetAndExerciseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public SetAndExerciseAdapter(ItemClickCallback callback) {
        mCallback = callback;
    }

    public void setList(List<RepeatableObject> items) {
        mItems = items;
    }

    public void setContent(@NonNull List<Set> sets) {
        mItems = new ArrayList<>();
        for (Set set : sets) {
            mItems.add(set);
            mItems.addAll(set.getExercises());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.get(position) instanceof Set) {
            return SET_ITEM_VIEW_TYPE;
        }
        else if (mItems.get(position) instanceof Exercise) {
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
                ItemSetBinding setBinding = DataBindingUtil.inflate(inflater, R.layout.item_set, parent, false);
                return new SetViewHolder(setBinding);
            case EXERCISE_ITEM_VIEW_TYPE:
                ItemExerciseBinding exerciseBinding = DataBindingUtil.inflate(inflater, R.layout.item_exercise, parent, false);
                exerciseBinding.setCallback(mCallback);
                return new ExerciseViewHolder(exerciseBinding);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case SET_ITEM_VIEW_TYPE:
                SetViewHolder setHolder = (SetViewHolder) holder;
                Set set = (Set) mItems.get(position);
                setHolder.mBinding.setName("Set");
                setHolder.mBinding.setReps(set.getReps());
                break;
            case EXERCISE_ITEM_VIEW_TYPE:
                ExerciseViewHolder exerciseHolder = (ExerciseViewHolder) holder;
                Exercise exercise = (Exercise) mItems.get(position);
                exerciseHolder.mBinding.setName(exercise.getExerciseName());
                exerciseHolder.mBinding.setReps(exercise.getReps());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    class SetViewHolder extends RecyclerView.ViewHolder {
        private ItemSetBinding mBinding;

        SetViewHolder(ItemSetBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private ItemExerciseBinding mBinding;

        ExerciseViewHolder(ItemExerciseBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private List<RepeatableObject> mItems;
    private ItemClickCallback mCallback;

    private static final int SET_ITEM_VIEW_TYPE = 1;
    private static final int EXERCISE_ITEM_VIEW_TYPE = 2;
}
