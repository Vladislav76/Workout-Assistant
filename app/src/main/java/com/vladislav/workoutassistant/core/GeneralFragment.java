package com.vladislav.workoutassistant.core;

import android.content.Context;

import com.vladislav.workoutassistant.core.callbacks.OnFragmentListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class GeneralFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentListener) {
            mFragmentListener = (OnFragmentListener) context;
        }
        else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mFragmentListener = null;
    }

    public void updateToolbar(String title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    public void updateToolbar(int resId) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(resId);
    }

    protected OnFragmentListener mFragmentListener;
}
