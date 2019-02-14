package com.vladislav.workoutassistant.data.model;

public class SelectableItem {

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

    private String mName;
    private boolean mSelected;
}
