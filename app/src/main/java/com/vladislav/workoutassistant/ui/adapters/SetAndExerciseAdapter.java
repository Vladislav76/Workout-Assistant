package com.vladislav.workoutassistant.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.model.Exercise;
import com.vladislav.workoutassistant.data.model.Program;
import com.vladislav.workoutassistant.data.model.RepeatableObject;
import com.vladislav.workoutassistant.data.model.Set;
import com.vladislav.workoutassistant.databinding.ExerciseItemBinding;
import com.vladislav.workoutassistant.databinding.SetItemBinding;
import com.vladislav.workoutassistant.ui.callbacks.ItemClickCallback;

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
                SetItemBinding setBinding = DataBindingUtil.inflate(inflater, R.layout.set_item, parent, false);
                return new SetViewHolder(setBinding);
            case EXERCISE_ITEM_VIEW_TYPE:
                ExerciseItemBinding exerciseBinding = DataBindingUtil.inflate(inflater, R.layout.exercise_item, parent, false);
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
        private SetItemBinding mBinding;

        SetViewHolder(SetItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private ExerciseItemBinding mBinding;

        ExerciseViewHolder(ExerciseItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    private List<RepeatableObject> mItems;
    private ItemClickCallback mCallback;

    private static final int SET_ITEM_VIEW_TYPE = 1;
    private static final int EXERCISE_ITEM_VIEW_TYPE = 2;
}
