package com.vladislav.workoutassistant.data.models;

public class SelectableItem {

    private String mName;
    private boolean mSelected;

    public SelectableItem(String name, boolean isSelected) {
        mName = name;
        mSelected = isSelected;
    }

    public String getName() {
        return mName;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean isSelected) {
        mSelected = isSelected;
    }
}
