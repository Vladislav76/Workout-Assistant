package com.vladislav.workoutassistant.diary.viewmodels;

import com.vladislav.workoutassistant.data.model.SelectableItem;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class SelectableItemViewModel extends BaseObservable {

    private SelectableItem mSelectableItem;

    @Bindable
    public String getName() {
        return mSelectableItem == null ? null : mSelectableItem.getName();
    }

    @Bindable
    public boolean isSelected() {
        return mSelectableItem != null && mSelectableItem.isSelected();
    }

    public void setSelectableItem(SelectableItem item) {
        mSelectableItem = item;
        notifyChange();
    }

    public void setSelected(boolean isSelected) {
        mSelectableItem.setSelected(isSelected);
        notifyChange();
    }
}
