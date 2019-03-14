package com.vladislav.workoutassistant.workouts.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.workouts.viewmodels.HorizontalModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalCardAdapter extends RecyclerView.Adapter<HorizontalCardAdapter.HorizontalCardViewHolder> {

    private List<HorizontalModel> mCards;

    public HorizontalCardAdapter() { }

    public HorizontalCardAdapter(List<HorizontalModel> cards) {
        mCards = cards;
    }

    public void setList(List<HorizontalModel> cards) {
        if (mCards == null) {
            mCards = cards;
//            notifyItemRangeInserted(0, mCards.size());
        }
        else {
            int oldSize = mCards.size();
            mCards = cards;
            int newSize = mCards.size();
//            if (newSize < oldSize) {
//                notifyItemRangeChanged(0, newSize);
//                notifyItemRangeRemoved(newSize, oldSize - newSize);
//            }
//            else {
//                notifyItemRangeChanged(0, oldSize);
//                notifyItemRangeInserted(oldSize, newSize - oldSize);
//            }
        }
    }

    @Override
    @NonNull
    public HorizontalCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_workout, parent, false);
        return new HorizontalCardAdapter.HorizontalCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalCardViewHolder holder, int position) {
        holder.bind(mCards.get(position));
    }

    @Override
    public int getItemCount() {
        return mCards == null ? 0 : mCards.size();
    }

    class HorizontalCardViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleView;
        private TextView mSubtitleView;

        HorizontalCardViewHolder(View view) {
            super(view);
            mTitleView = view.findViewById(R.id.card_title);
            mSubtitleView = view.findViewById(R.id.card_subtitle);
        }

        void bind(HorizontalModel card) {
            mTitleView.setText(card.getTitle());
            mSubtitleView.setText(card.getSubTitle());
        }
    }

    //TODO: add DiffUtil for setList method
}
