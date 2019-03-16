package com.vladislav.workoutassistant.workouts.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.db.entity.Workout;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.HorizontalCardViewHolder> {

    private List<Workout> mWorkouts;

    public void setList(List<Workout> workouts) {
        mWorkouts = workouts;   //TODO: add DiffUtil
    }

    @Override
    @NonNull
    public HorizontalCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_workout, parent, false);
        return new WorkoutAdapter.HorizontalCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalCardViewHolder holder, int position) {
        holder.bind(mWorkouts.get(position));
    }

    @Override
    public int getItemCount() {
        return mWorkouts == null ? 0 : mWorkouts.size();
    }

    class HorizontalCardViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleView;
        private TextView mSubtitleView;

        HorizontalCardViewHolder(View view) {
            super(view);
            mTitleView = view.findViewById(R.id.card_title);
            mSubtitleView = view.findViewById(R.id.card_subtitle);
        }

        void bind(Workout workout) {
            mTitleView.setText(workout.getName());
            mSubtitleView.setText(Integer.toString(workout.getId()));
        }
    }
}
