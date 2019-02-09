package com.vladislav.workoutassistant.view.activities;

import androidx.fragment.app.Fragment;

import com.vladislav.workoutassistant.view.fragments.SelectableDiaryFragment;

public class DeleteDiaryEntriesActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SelectableDiaryFragment();
    }
}
