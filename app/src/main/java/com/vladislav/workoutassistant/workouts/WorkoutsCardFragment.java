package com.vladislav.workoutassistant.workouts;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class WorkoutsCardFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.card_workouts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

    }

    public static WorkoutsCardFragment newInstance(String fragmentTag) {
        Bundle args = new Bundle();
        args.putString(FRAGMENT_TAG_ARG, fragmentTag);

        WorkoutsCardFragment fragment = new WorkoutsCardFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private static final String FRAGMENT_TAG_ARG = "fragment_tag_arg";

    public static final String MY_WORKOUTS_FRAGMENT_TAG = "my_workouts_fragment_tag";
}
