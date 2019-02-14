package com.vladislav.workoutassistant.data.model;

import java.sql.Time;
import java.util.ArrayList;

public interface FullDiaryEntry extends AbbreviatedDiaryEntry {

    Time getStartTime();
    Time getFinishTime();
    ArrayList<Integer> getMuscleGroupsIds();
    String getDefaultTitle();
    boolean isDefaultTitleChecked();
}
