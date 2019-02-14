package com.vladislav.workoutassistant.ui.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class TimePickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date time = (Date) getArguments().getSerializable(ARG_TIME);
        if (time == null) {
            time = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar calendar = new GregorianCalendar(0,0 ,0, hourOfDay, minute);
                        sendResult(Activity.RESULT_OK, new Time(calendar.getTime().getTime()));
                    }
                },
                hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    private void sendResult(int resultCode, Date date) {
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_TIME, date);
            targetFragment.onActivityResult(getTargetRequestCode(), resultCode, intent);
        }
    }

    public static TimePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private static final String ARG_TIME = "arg_time";
    public static final String EXTRA_TIME = "extra_time";
}
