package com.vladislav.workoutassistant.ui.activities;

import com.vladislav.workoutassistant.ui.fragments.SelectableDiaryFragment;

import androidx.fragment.app.Fragment;

public class DeleteDiaryEntriesActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SelectableDiaryFragment();
    }
}
