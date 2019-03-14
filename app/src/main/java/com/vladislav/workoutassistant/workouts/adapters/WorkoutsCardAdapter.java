package com.vladislav.workoutassistant.workouts.adapters;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.workouts.viewmodels.VerticalModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutsCardAdapter extends RecyclerView.Adapter<WorkoutsCardAdapter.WorkoutsCardViewHolder> {

    private List<WorkoutsCardInfo> mCardsInfo;
    private RecyclerView.RecycledViewPool mViewPool; //for the same view types

    public WorkoutsCardAdapter(List<VerticalModel> cards) {
        mViewPool = new RecyclerView.RecycledViewPool();
        mCardsInfo = new ArrayList<>();
        for (VerticalModel card : cards) {
            mCardsInfo.add(new WorkoutsCardInfo(card));
        }
    }

    public void setList(List<VerticalModel> cards) {
        int oldSize = mCardsInfo.size();
        mCardsInfo = new ArrayList<>();
        for (VerticalModel card : cards) {
            mCardsInfo.add(new WorkoutsCardInfo(card));
        }
        int newSize = mCardsInfo.size();
        if (newSize < oldSize) {
            notifyItemRangeChanged(0, newSize);
            notifyItemRangeRemoved(newSize, oldSize - newSize);
        }
        else {
            notifyItemRangeChanged(0, oldSize);
            notifyItemRangeInserted(oldSize, newSize - oldSize);
        }

        //        int oldSize = mCardsInfo.size();
//        int currentSize = 0;
//        for (VerticalModel card : cards) {
//            if (currentSize < oldSize) {
//                WorkoutsCardInfo info = mCardsInfo.get(currentSize);
//                info.getCard().
//                info.setCard(card);
//                info.setState(null);
//                info.setValid(false);
//            }
//            else {
//                mCardsInfo.add(new WorkoutsCardInfo(card));
//            }
//            currentSize++;
//        }
//        if (currentSize < oldSize) {
//            for (int i = oldSize - 1; i >= currentSize; i--) {
//                mCardsInfo.get(i).setValid(false);
//                mCardsInfo.remove(i);
//            }
////            notifyItemRangeRemoved(currentSize, oldSize - currentSize);
//        }
        System.out.println(Thread.currentThread());
//        notifyItemRangeChanged(0, mCardsInfo.size());
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
        holder.bind(mCardsInfo.get(position));
    }

    @Override
    public int getItemCount() {
        return mCardsInfo == null ? 0 : mCardsInfo.size();
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

    @Override
    public void onViewRecycled(@NonNull WorkoutsCardViewHolder holder) {
        System.out.println(holder.getAdapterPosition());
    }

    private WorkoutsCardInfo getInfo(VerticalModel card) {
        for (WorkoutsCardInfo cardInfo : mCardsInfo) {
            if (cardInfo.getCard().equals(card)) {
                return cardInfo;
            }
        }
        return null;
    }

    class WorkoutsCardViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleView;
        private RecyclerView mRecyclerView;
        private WorkoutsCardInfo mCardInfo;
        private HorizontalCardAdapter mAdapter;
//        private VerticalModel mCard;

        WorkoutsCardViewHolder(View view) {
            super(view);
            mTitleView = view.findViewById(R.id.card_title);
            mRecyclerView = view.findViewById(R.id.recycler_view);
            mRecyclerView.setRecycledViewPool(mViewPool);
            mAdapter = new HorizontalCardAdapter();
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setItemViewCacheSize(0);
        }

        void bind(WorkoutsCardInfo cardInfo) {
            System.out.println("Binded");
            mCardInfo = cardInfo;
//            mCardInfo.setValid(true);
//            mCard = card;
            mTitleView.setText(cardInfo.getCard().getTitle());
            mAdapter.setList(cardInfo.getCard().getList());
            mAdapter.notifyDataSetChanged();
//            mRecyclerView.setAdapter(mAdapter);
        }

        void saveRecyclerViewPosition() {

            if (mCardsInfo.contains(mCardInfo)) {
                System.out.println("State saved");
//                WorkoutsCardInfo cardInfo = getInfo(mCard);
                mCardInfo.setState(mRecyclerView.getLayoutManager().onSaveInstanceState());
            }
        }

        void restoreRecyclerViewPosition() {
            if (mCardsInfo.contains(mCardInfo)) {
//                WorkoutsCardInfo cardInfo = getInfo(mCard);
                mRecyclerView.getLayoutManager().onRestoreInstanceState(mCardInfo.getState());
            }
        }
    }

    class WorkoutsCardInfo {
        private VerticalModel mCard;
        private Parcelable mState;
        private boolean mIsValid;

        WorkoutsCardInfo(VerticalModel card) {
            mCard = card;
        }

        VerticalModel getCard() {
            return mCard;
        }

        Parcelable getState() {
            return mState;
        }

        boolean isValid() {
            return mIsValid;
        }

        void setCard(VerticalModel card) {
            mCard = card;
        }

        void setState(Parcelable state) {
            mState = state;
        }

        void setValid(boolean isValid) {
            mIsValid = isValid;
        }
    }
}