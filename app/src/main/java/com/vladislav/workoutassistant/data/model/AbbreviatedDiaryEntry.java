package com.vladislav.workoutassistant.data.model;

import java.util.Date;

public interface AbbreviatedDiaryEntry {

    int getId();
    String getTitle();
    Date getDate();
    Date getDuration();
    boolean isSelected();
    void setSelected(boolean isSelected);
}
