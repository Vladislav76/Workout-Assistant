package com.vladislav.workoutassistant.view.activities;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import com.vladislav.workoutassistant.data.Diary;
import com.vladislav.workoutassistant.view.fragments.WorkoutDetailsFragment;

public class WorkoutDetailsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        int diaryEntryId = getIntent().getIntExtra(EXTRA_DIARY_ENTRY_ID, Diary.NULL_TEMP_DIARY_ENTRY);
        Diary.getDiary().setTempDiaryEntry(diaryEntryId);

        return WorkoutDetailsFragment.newInstance();
    }

    public static Intent newIntent(Context packageContext, int diaryEntryId) {
        Intent intent = new Intent(packageContext, WorkoutDetailsActivity.class);
        intent.putExtra(EXTRA_DIARY_ENTRY_ID, diaryEntryId);
        return intent;
    }

    private static final String EXTRA_DIARY_ENTRY_ID = "diary_entry_id";
}
