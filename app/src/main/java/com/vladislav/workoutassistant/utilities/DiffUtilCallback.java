package com.vladislav.workoutassistant.utilities;

import com.vladislav.workoutassistant.data.models.Identifiable;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class DiffUtilCallback extends DiffUtil.Callback {

    private List<? extends Identifiable> mOldList;
    private List<? extends Identifiable> mNewList;

    public DiffUtilCallback(List<? extends Identifiable> oldList, List<? extends Identifiable> newList) {
        mOldList = oldList;
        mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).getId() == mNewList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).equals(mNewList.get(newItemPosition));
    }
}
