package com.vladislav.workoutassistant.ui;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.ui.callbacks.OnBackButtonListener;
import com.vladislav.workoutassistant.ui.callbacks.OnFragmentListener;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        OnFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mMainPanel = findViewById(R.id.main_panel);
        mMainPanel.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            onNavigationItemSelected(mMainPanel.getMenu().getItem(0));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        String tag = Integer.toString(item.getItemId());
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        if (fragment == null) {
            switch (item.getItemId()) {
                case R.id.programs_action:
                    fragment = ProgramCategoriesFragment.newInstance();
                    break;
                case R.id.diary_action:
                    fragment = DiaryFragment.newInstance();
                    break;
                default:
                    return false;
            }
        }

        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment, tag)
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit();
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

    private Toolbar mToolbar;
    private BottomNavigationView mMainPanel;

    private static final String BACK_STACK_ROOT_TAG = "root_fragment";
}
