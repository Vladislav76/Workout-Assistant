package com.vladislav.workoutassistant.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.Diary;
import com.vladislav.workoutassistant.data.DiaryEntry;
import com.vladislav.workoutassistant.databinding.FragmentWorkoutDetailsBinding;
import com.vladislav.workoutassistant.viewmodel.DiaryEntryViewModel;

import java.sql.Time;
import java.util.ArrayList;
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

        mMuscleGroupsNameArray = getResources().getStringArray(R.array.muscle_groups);
        updateMuscleGroups();

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
                    updateDuration();
                    break;
                case REQUEST_GET_FINISH_TIME:
                    date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
                    mDiaryEntryViewModel.setFinishTime(date);
                    updateDuration();
                    break;
                case REQUEST_GET_SELECTED_MUSCLE_GROUPS_ID_LIST:
                    ArrayList<Integer> selectedMuscleGroupsIdList = data.getIntegerArrayListExtra(ItemGroupPickerFragment.EXTRA_SELECTED_ITEM_ID_LIST);
                    mDiaryEntryViewModel.setMuscleGroupsIds(selectedMuscleGroupsIdList);
                    updateMuscleGroups();
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

    public void onMuscleGroupsEditingClicked(View view) {
        FragmentManager fm = getFragmentManager();
        DialogFragment dialog = ItemGroupPickerFragment
                .newInstance(mDiaryEntryViewModel.getMuscleGroupsIds(), mMuscleGroupsNameArray);
        dialog.setTargetFragment(this, REQUEST_GET_SELECTED_MUSCLE_GROUPS_ID_LIST);
        dialog.show(fm, TAG_ITEM_GROUP_PICKER_DIALOG);
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

    private void updateDuration() {
        if (mDiaryEntryViewModel.getStartTime() != null && mDiaryEntryViewModel.getFinishTime() != null) {
            Time startTime = (Time) mDiaryEntryViewModel.getDiaryEntry().getStartTime();
            Time finishTime = (Time) mDiaryEntryViewModel.getDiaryEntry().getFinishTime();
            if (startTime.toString().compareTo(finishTime.toString()) <= 0) {
                Time duration = new Time(Math.abs(finishTime.getTime() - startTime.getTime()));
                mDiaryEntryViewModel.setDuration(duration);
            }
            else {
                mDiaryEntryViewModel.setDuration(null);
                Toast.makeText(getActivity(), R.string.not_correct_time_input_error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateMuscleGroups() {
        ChipGroup chipGroup = mBinding.muscleGroupsChips;
        int chipsNumber = chipGroup.getChildCount();
        ArrayList<Integer> selectedMuscleGroupsIdList = mDiaryEntryViewModel.getMuscleGroupsIds();

        if (selectedMuscleGroupsIdList != null) {
            for (int i = 0; i < selectedMuscleGroupsIdList.size(); i++) {
                if (i < chipsNumber - 1) {
                    Chip chip = (Chip) chipGroup.getChildAt(i);
                    chip.setText(mMuscleGroupsNameArray[selectedMuscleGroupsIdList.get(i)]);
                }
                else {
                    Chip chip = new Chip(getActivity());
                    chip.setText(mMuscleGroupsNameArray[selectedMuscleGroupsIdList.get(i)]);
                    chipGroup.addView(chip, chipsNumber - 1);
                    chipsNumber++;
                }
            }

            for (int i = chipsNumber - 2; i >= selectedMuscleGroupsIdList.size() ; i--) {
                chipGroup.removeViewAt(i);
            }
        }
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
    private String[] mMuscleGroupsNameArray;

    private static final String ARG_DIARY_ENTRY_ID = "arg_diary_entry_id";
    private static final String TAG_DATE_PICKER_DIALOG = "date_picker_dialog";
    private static final String TAG_TIME_PICKER_DIALOG = "time_picker_dialog";
    private static final String TAG_ITEM_GROUP_PICKER_DIALOG = "item_group_picker_dialog";
    private static final int REQUEST_GET_DATE = 1;
    private static final int REQUEST_GET_START_TIME = 2;
    private static final int REQUEST_GET_FINISH_TIME = 3;
    private static final int REQUEST_GET_SELECTED_MUSCLE_GROUPS_ID_LIST = 4;
}
