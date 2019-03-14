package com.vladislav.workoutassistant.core.callbacks;

import com.vladislav.workoutassistant.diary.viewmodels.DiaryEntryViewModel;

public interface DiaryEntryClickCallback {

    void onClick(DiaryEntryViewModel model, String name);
}
