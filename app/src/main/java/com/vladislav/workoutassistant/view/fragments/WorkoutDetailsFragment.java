package com.vladislav.workoutassistant.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.Diary;
import com.vladislav.workoutassistant.data.DiaryEntry;
import com.vladislav.workoutassistant.databinding.FragmentWorkoutDetailsBinding;
import com.vladislav.workoutassistant.viewmodel.DiaryEntryViewModel;

import java.util.Date;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class WorkoutDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_workout_details, container, false);

        int diaryEntryId = getArguments().getInt(ARG_DIARY_ENTRY_ID, 0);
        DiaryEntry diaryEntry = Diary.getDiary().getEntryById(diaryEntryId);

        mDiaryEntryViewModel = new DiaryEntryViewModel();
        mDiaryEntryViewModel.setDiaryEntry(diaryEntry);
        mBinding.setViewModel(mDiaryEntryViewModel);
        mBinding.setFragment(this);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case REQUEST_GET_DATE:
                    Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
                    mDiaryEntryViewModel.setDate(date);
                    break;
                case REQUEST_GET_START_TIME:
                    date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
                    mDiaryEntryViewModel.setStartTime(date);
                    break;
                case REQUEST_GET_FINISH_TIME:
                    date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
                    mDiaryEntryViewModel.setFinishTime(date);
                    break;
            }
        }
    }

    public void onDateOrTimeButtonClicked(View view) {
        Date date = getDate(view.getId());
        FragmentManager fm = getFragmentManager();
        DialogFragment dialog;

        switch (view.getId()) {
            case R.id.date_button:
                dialog = DatePickerFragment.newInstance(date);
                dialog.setTargetFragment(this, REQUEST_GET_DATE);
                dialog.show(fm, TAG_DATE_PICKER_DIALOG);
                break;
            case R.id.start_time_button:
                dialog = TimePickerFragment.newInstance(date);
                dialog.setTargetFragment(this, REQUEST_GET_START_TIME);
                dialog.show(fm, TAG_TIME_PICKER_DIALOG);
                break;
            case R.id.finish_time_button:
                dialog = TimePickerFragment.newInstance(date);
                dialog.setTargetFragment(this, REQUEST_GET_FINISH_TIME);
                dialog.show(fm, TAG_TIME_PICKER_DIALOG);
                break;
        }
    }

    private Date getDate(int buttonId) {
        DiaryEntry entry = mDiaryEntryViewModel.getDiaryEntry();
        if (entry == null) {
            return new Date();
        }

        switch (buttonId) {
            case R.id.date_button:
                return entry.getDate();
            case R.id.start_time_button:
                return entry.getStartTime();
            case R.id.finish_time_button:
                return entry.getFinishTime();
        }
        return null;
    }

    public static WorkoutDetailsFragment newInstance(int diaryEntryId) {
        Bundle args = new Bundle();
        args.putInt(ARG_DIARY_ENTRY_ID, diaryEntryId);

        WorkoutDetailsFragment fragment = new WorkoutDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private DiaryEntryViewModel mDiaryEntryViewModel;
    private FragmentWorkoutDetailsBinding mBinding;

    private static final String ARG_DIARY_ENTRY_ID = "arg_diary_entry_id";
    private static final String TAG_DATE_PICKER_DIALOG = "date_picker_dialog";
    private static final String TAG_TIME_PICKER_DIALOG = "time_picker_dialog";
    private static final int REQUEST_GET_DATE = 1;
    private static final int REQUEST_GET_START_TIME = 2;
    private static final int REQUEST_GET_FINISH_TIME = 3;
}
