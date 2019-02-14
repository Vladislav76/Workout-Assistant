package com.vladislav.workoutassistant.ui.activities;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.ui.fragments.ProgramsFragment;
import com.vladislav.workoutassistant.ui.fragments.DiaryFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView mainPanel = findViewById(R.id.main_panel);
        mainPanel.setOnNavigationItemSelectedListener(new MainPanelListener());
    }

    private class MainPanelListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            String fragmentTag = null;
            switch (item.getItemId()) {
                case R.id.action_programs:
                    fragment = new ProgramsFragment();
                    fragmentTag = PROGRAMS_FRAGMENT_TAG;
                    break;
                case R.id.action_diary:
                    fragment = new DiaryFragment();
                    fragmentTag = DIARY_FRAGMENT_TAG;
                    break;
            }
            if (fragment != null) {
                FragmentTransaction fragTrans = getSupportFragmentManager().beginTransaction();
                fragTrans.replace(R.id.content_frame, fragment, fragmentTag);
                fragTrans.commit();
                return true;
            }
            else {
                return false;
            }
        }
    }

    private static final String PROGRAMS_FRAGMENT_TAG = "programs_fragment_tag";
    private static final String DIARY_FRAGMENT_TAG = "diary_fragment_tag";
}
