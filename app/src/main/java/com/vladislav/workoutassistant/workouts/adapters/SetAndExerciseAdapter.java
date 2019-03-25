package com.vladislav.workoutassistant.workouts.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.model.NamedObject;
import com.vladislav.workoutassistant.data.model.WorkoutExercise;
import com.vladislav.workoutassistant.data.model.WorkoutSet;
import com.vladislav.workoutassistant.core.callbacks.ItemClickCallback;
import com.vladislav.workoutassistant.databinding.ItemWorkoutExerciseBinding;
import com.vladislav.workoutassistant.databinding.ItemWorkoutSetBinding;
import com.vladislav.workoutassistant.workouts.components.SimpleItemTouchHelperCallback;
import com.vladislav.workoutassistant.workouts.components.SimpleItemTouchHelperCallback.OnStartDragListener;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class SetAndExerciseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SimpleItemTouchHelperCallback.ItemTouchHelperAdapter {

    private static final int SET_ITEM_VIEW_TYPE = 1;
    private static final int EXERCISE_ITEM_VIEW_TYPE = 2;

    private List<Object> mItems;
    private List<NamedObject> mMuscleGroups;
    private ItemClickCallback mCallback;
    private OnStartDragListener mStartDragListener;

    public SetAndExerciseAdapter(ItemClickCallback callback, OnStartDragListener startDragListener, List<NamedObject> muscleGroups) {
        mCallback = callback;
        mStartDragListener = startDragListener;
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
        } else if (holder instanceof ExerciseViewHolder) {
            ((ExerciseViewHolder) holder).bind((WorkoutExercise) mItems.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mItems, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mItems, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
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

    class ExerciseViewHolder extends RecyclerView.ViewHolder implements SimpleItemTouchHelperCallback.ItemTouchHelperViewHolder {
        private ItemWorkoutExerciseBinding mBinding;

        ExerciseViewHolder(ItemWorkoutExerciseBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(WorkoutExercise exercise) {
            mBinding.setName(exercise.exerciseInfo.getName());
            mBinding.setReps(exercise.matchingInfo.getReps());
            mBinding.setMuscleGroup(mMuscleGroups.get(exercise.exerciseInfo.getMuscleGroupId()).getName());
            mBinding.handle.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        mStartDragListener.onStartDrag(ExerciseViewHolder.this);
                    }
                    return false;
                }
            });
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
