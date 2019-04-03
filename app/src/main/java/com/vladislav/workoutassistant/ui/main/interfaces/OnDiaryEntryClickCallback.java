package com.vladislav.workoutassistant.ui.main.interfaces;

import com.vladislav.workoutassistant.ui.diary.viewmodels.DiaryEntryViewModel;

public interface OnDiaryEntryClickCallback {

    void onClick(DiaryEntryViewModel model, String name);
}
