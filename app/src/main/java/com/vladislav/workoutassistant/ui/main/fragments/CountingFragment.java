package com.vladislav.workoutassistant.ui.main.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.vladislav.workoutassistant.R;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class CountingFragment extends Fragment implements View.OnClickListener {

    public enum MeasureUnit {
        KM, KG
    }

    private static final int MINIMUM_COUNT = 0;
    private static final int MAXIMUM_COUNT = 99999;

    private static final String COUNT_ARG = "count_arg";
    private static final String MEASURE_UNITS_ARG = "measure_units_arg";

    private static final String STORED_DATA = "stored_data";

    private EditText mCountField;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_count_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        mCountField = view.findViewById(R.id.count_field);

        ImageButton decrementButton = view.findViewById(R.id.decrement_button);
        decrementButton.setOnClickListener(this);

        ImageButton incrementButton = view.findViewById(R.id.increment_button);
        incrementButton.setOnClickListener(this);

        if (savedInstanceState == null) {
            recountAndUpdate(getCurrentValue(),0);
            Log.d("COUNTING_FRAGMENT", "BUNDLE NULL");
        } else {
            Log.d("COUNTING_FRAGMENT", "BUNDLE NOT NULL");
            int currentCount = savedInstanceState.getInt(STORED_DATA);
            recountAndUpdate(currentCount, 0);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putInt(STORED_DATA, getCurrentValue());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.decrement_button:
                recountAndUpdate(getCurrentValue(), -1);
                break;
            case R.id.increment_button:
                recountAndUpdate(getCurrentValue(),1);
                break;
        }
    }

    public int getCurrentValue() {
        String string = mCountField.getText().toString();
        return string.equals("") ? getArguments().getInt(COUNT_ARG) : Integer.parseInt(string);
    }

    /* F A StringConverter T O R Y */
    public static CountingFragment newInstance(int count, MeasureUnit unit) {
        Bundle args = new Bundle();
        args.putInt(COUNT_ARG, count);
        args.putSerializable(MEASURE_UNITS_ARG, unit);

        CountingFragment fragment = new CountingFragment();
        fragment.setArguments(args);

        return fragment;
    }

    /* P R I V A T E */
    private void recountAndUpdate(int value, int offset) {
        int newValue = value + offset;
        mCountField.setText(String.format(Locale.getDefault(), "%d",
                newValue > MINIMUM_COUNT ? (newValue < MAXIMUM_COUNT ? newValue : MAXIMUM_COUNT) : MINIMUM_COUNT));
    }
}
