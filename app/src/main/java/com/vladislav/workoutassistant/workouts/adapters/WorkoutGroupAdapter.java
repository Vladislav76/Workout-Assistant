package com.vladislav.workoutassistant.workouts.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.model.NamedObject;
import com.vladislav.workoutassistant.data.model.WorkoutGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutGroupAdapter extends RecyclerView.Adapter<WorkoutGroupAdapter.WorkoutsCardViewHolder> {

    private List<WorkoutGroup> mWorkoutGroups;
    private List<NamedObject> mIntensityLevels;
    private RecyclerView.RecycledViewPool mViewPool; //for the same view types

    public WorkoutGroupAdapter(List<NamedObject> intensityLevels) {
        mViewPool = new RecyclerView.RecycledViewPool();
        mIntensityLevels = intensityLevels;
    }

    public void setList(List<WorkoutGroup> groups) {
        mWorkoutGroups = groups;   //TODO: add DiffUtil
    }

    @Override
    @NonNull
    public WorkoutsCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_workouts, parent, false);
        return new WorkoutsCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutsCardViewHolder holder, int position) {
        holder.bind(mWorkoutGroups.get(position));
    }

    @Override
    public int getItemCount() {
        return mWorkoutGroups == null ? 0 : mWorkoutGroups.size();
    }

//    @Override
//    public void onViewAttachedToWindow(@NonNull WorkoutsCardViewHolder holder) {
//        holder.restoreRecyclerViewPosition();
//        System.out.println("Attached");
//        super.onViewAttachedToWindow(holder);
//        System.out.println(holder.getItemId());
//    }
//
//    @Override
//    public void onViewDetachedFromWindow(@NonNull WorkoutsCardViewHolder holder) {
//        holder.saveRecyclerViewPosition();
//        System.out.println("Detached");
//        super.onViewDetachedFromWindow(holder);
//    }

//    @Override
//    public void onViewRecycled(@NonNull WorkoutsCardViewHolder holder) {
//        System.out.println(holder.getAdapterPosition());
//    }

    class WorkoutsCardViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleView;
        private RecyclerView mRecyclerView;
        private WorkoutAdapter mAdapter;

        WorkoutsCardViewHolder(View view) {
            super(view);
            mTitleView = view.findViewById(R.id.card_title);
            mRecyclerView = view.findViewById(R.id.recycler_view);
            mRecyclerView.setRecycledViewPool(mViewPool);
            mAdapter = new WorkoutAdapter();
            mRecyclerView.setAdapter(mAdapter);
        }

        void bind(WorkoutGroup group) {
            mTitleView.setText(mIntensityLevels.get(group.getGroupId()).getName());
            mAdapter.setList(group.getWorkouts());
            mAdapter.notifyDataSetChanged();
            mRecyclerView.scrollToPosition(0);
        }

//        void saveRecyclerViewPosition() {
//            if (mCardsInfo.contains(mCardInfo)) {
//                System.out.println("State saved");
////                WorkoutsCardInfo cardInfo = getInfo(mCard);
//                mCardInfo.setState(mRecyclerView.getLayoutManager().onSaveInstanceState());
//            }
//        }
//
//        void restoreRecyclerViewPosition() {
//            if (mCardsInfo.contains(mCardInfo)) {
////                WorkoutsCardInfo cardInfo = getInfo(mCard);
//                mRecyclerView.getLayoutManager().onRestoreInstanceState(mCardInfo.getState());
//            }
//        }
    }

//    class WorkoutsCardInfo {
//        private WorkoutGroupListViewModel mCard;
//        private Parcelable mState;
//        private boolean mIsValid;
//
//        WorkoutsCardInfo(WorkoutGroupListViewModel card) {
//            mCard = card;
//        }
//
//        WorkoutGroupListViewModel getCard() {
//            return mCard;
//        }
//
//        Parcelable getState() {
//            return mState;
//        }
//
//        boolean isValid() {
//            return mIsValid;
//        }
//
//        void setCard(WorkoutGroupListViewModel card) {
//            mCard = card;
//        }
//
//        void setState(Parcelable state) {
//            mState = state;
//        }
//
//        void setValid(boolean isValid) {
//            mIsValid = isValid;
//        }
//    }
}