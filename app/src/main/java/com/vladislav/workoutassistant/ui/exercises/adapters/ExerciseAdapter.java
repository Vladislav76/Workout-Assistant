package com.vladislav.workoutassistant.ui.exercises.adapters;

import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.utilities.DiffUtilCallback;
import com.vladislav.workoutassistant.ui.main.interfaces.OnItemClickCallback;
import com.vladislav.workoutassistant.data.db.entity.Exercise;
import com.vladislav.workoutassistant.databinding.ItemExerciseBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>{

    private List<Exercise> mExercises;
    private SparseIntArray mSelectedExercises;
    private OnItemClickCallback mCallback;
    private int mLastClickedItemPosition;

    public ExerciseAdapter(OnItemClickCallback callback, SparseIntArray selectedExercises) {
        mCallback = callback;
        mSelectedExercises = selectedExercises;
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

    public void setLastClickedItemPosition(int position) {
        mLastClickedItemPosition = position;
    }

    public int getLastClickedItemPosition() {
        return mLastClickedItemPosition;
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

    class ExerciseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemExerciseBinding mBinding;
        private Exercise mExercise;

        ExerciseViewHolder(ItemExerciseBinding binding) {
            super(binding.getRoot());

            mBinding = binding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mLastClickedItemPosition = getAdapterPosition();
            mCallback.onClick(mExercise.getId(), mExercise.getName());
        }

        void bind(Exercise exercise) {
            mExercise = exercise;
            mBinding.setName(exercise.getName());
            int exerciseCount;
            if ((exerciseCount = mSelectedExercises.get(exercise.getId(), -1)) == -1) {
                mBinding.setSelected(false);
            } else {
                mBinding.setSelected(true);
                mBinding.setCount(exerciseCount);
            }
        }
    }
}
