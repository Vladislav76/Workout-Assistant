package com.vladislav.workoutassistant.workouts.viewmodels;

import java.util.ArrayList;
import java.util.List;

public class VerticalModel {

    private String mTitle;
    private List<HorizontalModel> mList;

    public VerticalModel(String title) {
        mTitle = title;
        mList = generateModels();
    }

    public String getTitle() {
        return mTitle;
    }

    public List<HorizontalModel> getList() {
        return mList;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setList(List<HorizontalModel> list) {
        mList = list;
    }

    /* PROTOTYPE DATA */
    private List<HorizontalModel> generateModels() {
        List<HorizontalModel> models = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            models.add(new HorizontalModel("Title " + i, "Subtitle " + i));
        }
        return models;
    }
}
