package com.vladislav.workoutassistant.ui.main.interfaces;

import androidx.fragment.app.Fragment;

public interface OnFragmentListener {

    void addFragmentOnTop(Fragment fragment);
    void updateToolbarTitle(CharSequence title);
    void updateToolbarTitle(int resId);
}
