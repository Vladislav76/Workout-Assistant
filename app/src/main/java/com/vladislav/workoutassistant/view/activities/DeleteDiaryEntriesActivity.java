package com.vladislav.workoutassistant.view.activities;

import androidx.fragment.app.Fragment;

import com.vladislav.workoutassistant.view.fragments.DiaryFragment;

public class DeleteDiaryEntriesActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return DiaryFragment.newInstance(true);
    }
}
