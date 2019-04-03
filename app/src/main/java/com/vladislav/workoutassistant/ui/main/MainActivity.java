package com.vladislav.workoutassistant.ui.main;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.ui.diary.DiaryFragment;
import com.vladislav.workoutassistant.ui.main.interfaces.OnBackButtonListener;
import com.vladislav.workoutassistant.ui.main.interfaces.OnFragmentListener;
import com.vladislav.workoutassistant.ui.exercises.ExercisesFragment;
import com.vladislav.workoutassistant.ui.workouts.WorkoutsFragment;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, OnFragmentListener {

    private static final String BACK_STACK_ROOT_TAG = "root_fragment";

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        BottomNavigationView mainPanel = findViewById(R.id.main_panel);
        mainPanel.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            onNavigationItemSelected(mainPanel.getMenu().getItem(0));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        String tag = Integer.toString(item.getItemId());
        Fragment fragment = fragmentManager.findFragmentByTag(tag);

        if (fragment == null ) {
            switch (item.getItemId()) {
                case R.id.workouts_tab:
                    fragment = WorkoutsFragment.newInstance();
                    break;
                case R.id.exercises_tab:
                    fragment = ExercisesFragment.newInstance(false);
                    break;
                case R.id.diary_tab:
                    fragment = DiaryFragment.newInstance();
                    break;
                default:
                    return false;
            }
            fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment, tag)
                    .addToBackStack(BACK_STACK_ROOT_TAG)
                    .commit();
        }
        else {
            fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, 0);
        }

        return true;
    }

    @Override
    public void addFragmentOnTop(@NonNull Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void updateToolbarTitle(CharSequence title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void updateToolbarTitle(int resId) {
        mToolbar.setTitle(resId);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() > 1) {
            Fragment fragment = fragmentManager.findFragmentById(R.id.content_frame);
            if (!(fragment instanceof OnBackButtonListener) ||
                    !((OnBackButtonListener) fragment).onBackPressed()) {
                fragmentManager.popBackStackImmediate();
            }
        }
        else {
            supportFinishAfterTransition();
        }
    }
}
