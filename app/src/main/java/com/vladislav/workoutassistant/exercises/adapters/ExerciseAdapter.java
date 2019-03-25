package com.vladislav.workoutassistant.exercises.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.core.DiffUtilCallback;
import com.vladislav.workoutassistant.core.callbacks.ItemClickCallback;
import com.vladislav.workoutassistant.data.db.entity.Exercise;
import com.vladislav.workoutassistant.databinding.ItemExerciseBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>{

    private List<Exercise> mExercises;
    private ItemClickCallback mCallback;
    private boolean mIsMultipleSelectionMode;

    public ExerciseAdapter(ItemClickCallback callback, boolean isMultipleSelectionMode) {
        mCallback = callback;
        mIsMultipleSelectionMode = isMultipleSelectionMode;
    }

    public void updateList(List<Exercise> exercises) {
        if (mExercises == null) {
            mExercises = exercises;
            notifyItemRangeInserted(0, exercises.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallback(mExercises, exercises));
            mExercises = exercises;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @Override
    @NonNull
    public ExerciseAdapter.ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemExerciseBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_exercise, parent, false);
        return new ExerciseAdapter.ExerciseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ExerciseViewHolder holder, int position) {
        holder.bind(mExercises.get(position));
    }

    @Override
    public int getItemCount() {
        return mExercises == null ? 0 : mExercises.size();
    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private ItemExerciseBinding mBinding;
        private Exercise mExercise;

        ExerciseViewHolder(ItemExerciseBinding binding) {
            super(binding.getRoot());

            mBinding = binding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mIsMultipleSelectionMode) {
                        mBinding.setIsSelected(true);
                    }
                    mCallback.onClick(mExercise.getId(), mExercise.getName());
                }
            });
//            mBinding.setCallback(mCallback);
        }

        void bind(Exercise exercise) {
            mExercise = exercise;
            mBinding.setId(exercise.getId());
            mBinding.setName(exercise.getName());
        }
    }
}
