package com.vladislav.workoutassistant.ui.main.interfaces

import com.vladislav.workoutassistant.ui.diary.viewmodels.DiaryEntryViewModel

interface OnDiaryEntryClickCallback {

    fun onClick(model: DiaryEntryViewModel, name: String)
}
