package com.vladislav.workoutassistant.workouts.viewmodels;

public class HorizontalModel {

    public HorizontalModel(String title, String subTitle) {
        mTitle = title;
        mSubTitle = subTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setSubTitle(String subTitle) {
        mSubTitle = subTitle;
    }

    private String mTitle;
    private String mSubTitle;
}
