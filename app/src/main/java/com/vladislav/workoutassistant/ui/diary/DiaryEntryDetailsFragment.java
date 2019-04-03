package com.vladislav.workoutassistant.ui.diary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.db.entity.DiaryEntry;
import com.vladislav.workoutassistant.data.models.FullDiaryEntry;
import com.vladislav.workoutassistant.databinding.FragmentDiaryEntryDetailsBinding;
import com.vladislav.workoutassistant.ui.main.dialogs.DatePickerFragment;
import com.vladislav.workoutassistant.ui.main.GeneralFragment;
import com.vladislav.workoutassistant.ui.main.dialogs.ItemGroupPickerFragment;
import com.vladislav.workoutassistant.ui.main.dialogs.TimePickerFragment;
import com.vladislav.workoutassistant.ui.diary.viewmodels.DiaryEntryViewModel;
import com.vladislav.workoutassistant.ui.diary.viewmodels.DiaryEntryViewModelFactory;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DiaryEntryDetailsFragment extends GeneralFragment {

    private static final String DIARY_ENTRY_ID_ARG = "diary_entry_id_arg";
    private static final String DIARY_ENTRY_NAME_ARG = "diary_entry_name_arg";
    private static final String TAG_DATE_PICKER_DIALOG = "date_picker_dialog";
    private static final String TAG_TIME_PICKER_DIALOG = "time_picker_dialog";
    private static final String TAG_ITEM_GROUP_PICKER_DIALOG = "item_group_picker_dialog";
    private static final int REQUEST_GET_DATE = 1;
    private static final int REQUEST_GET_START_TIME = 2;
    private static final int REQUEST_GET_FINISH_TIME = 3;
    private static final int REQUEST_GET_SELECTED_MUSCLE_GROUPS_ID_LIST = 4;

    private DiaryEntryViewModel mDiaryEntryViewModel;
    private FragmentDiaryEntryDetailsBinding mBinding;
    private String[] mMuscleGroupsNameArray;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String title = getArguments().getString(DIARY_ENTRY_NAME_ARG);
        if (title == null) {
            mFragmentListener.updateToolbarTitle(R.string.new_diary_entry_toolbar_title);
        }
        else {
            mFragmentListener.updateToolbarTitle(title);
        }
        setHasOptionsMenu(true);

        DiaryEntryViewModelFactory factory = new DiaryEntryViewModelFactory(getActivity().getApplication(), getArguments().getInt(DIARY_ENTRY_ID_ARG));
        mDiaryEntryViewModel = ViewModelProviders.of(this, factory).get(DiaryEntryViewModel.class);
        mDiaryEntryViewModel.getDiaryEntry().observe(this, new Observer<DiaryEntry>() {
            @Override
            public void onChanged(DiaryEntry diaryEntry) {
                mDiaryEntryViewModel.setEntry(diaryEntry);
                updateMuscleGroups();
            }
        });

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_diary_entry_details, container, false);
        mBinding.setFragment(this);
        mBinding.setViewModel(mDiaryEntryViewModel);
        mBinding.setLifecycleOwner(this);
        mBinding.titleField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
        mBinding.titleField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mBinding.titleField.clearFocus();
                }
                return false;
            }
        });

        mMuscleGroupsNameArray = getResources().getStringArray(R.array.muscle_groups);

        return mBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.workout_details_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_entry_action:
                saveChanges();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
                    Time time = (Time) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
                    mDiaryEntryViewModel.setStartTime(time);
                    updateDuration();
                    break;
                case REQUEST_GET_FINISH_TIME:
                    time = (Time) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
                    mDiaryEntryViewModel.setFinishTime(time);
                    updateDuration();
                    break;
                case REQUEST_GET_SELECTED_MUSCLE_GROUPS_ID_LIST:
//                    ArrayList<Integer> selectedMuscleGroupsIdList = data.getIntegerArrayListExtra(ItemGroupPickerFragment.EXTRA_SELECTED_ITEM_ID_LIST);
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
                .newInstance(mDiaryEntryViewModel.getEntry().get().getMuscleGroupsIds(), mMuscleGroupsNameArray);
        dialog.setTargetFragment(this, REQUEST_GET_SELECTED_MUSCLE_GROUPS_ID_LIST);
        dialog.show(fm, TAG_ITEM_GROUP_PICKER_DIALOG);
    }

    public void saveChanges() {
        FullDiaryEntry diaryEntry = mDiaryEntryViewModel.getEntry().get();
        boolean isIncorrectData = false;
        if (diaryEntry.getStartTime() == null || diaryEntry.getFinishTime() == null) {
            isIncorrectData = true;
            Toast.makeText(getActivity(), R.string.null_time_input_error, Toast.LENGTH_SHORT).show();
        }
        else if (diaryEntry.getDuration() == null) {
            isIncorrectData = true;
            Toast.makeText(getActivity(), R.string.not_correct_time_input_error, Toast.LENGTH_SHORT).show();
        }
        if (!diaryEntry.isDefaultTitleChecked() && (diaryEntry.getTitle() == null || diaryEntry.getTitle().equals(""))) {
            isIncorrectData = true;
            mBinding.titleFieldWrapper.setError(getString(R.string.empty_title_error));
        }
        else {
            mBinding.titleFieldWrapper.setErrorEnabled(false);
        }

        if (!isIncorrectData) {
            if (diaryEntry.isDefaultTitleChecked()) {
                mDiaryEntryViewModel.setTitle(diaryEntry.getDefaultTitle());
            }
            if (getArguments().getInt(DIARY_ENTRY_ID_ARG) == DiaryEntryViewModel.NEW_DIARY_ENTRY_ID) {
                mDiaryEntryViewModel.insertEntry();
            }
            else {
                mDiaryEntryViewModel.updateEntry();
            }
            getActivity().onBackPressed();
        }
    }

    public static DiaryEntryDetailsFragment newInstance(int diaryEntryId, String diaryEntryName) {
        Bundle args = new Bundle();
        args.putInt(DIARY_ENTRY_ID_ARG, diaryEntryId);
        args.putString(DIARY_ENTRY_NAME_ARG, diaryEntryName);

        DiaryEntryDetailsFragment fragment = new DiaryEntryDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private Date getDate(int buttonId) {
        FullDiaryEntry entry = mDiaryEntryViewModel.getEntry().get();
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

    private void updateDefaultTitle() {
        ArrayList<Integer> muscleGroupsIds = mDiaryEntryViewModel.getEntry().get().getMuscleGroupsIds();
        String defaultTitle;
        if (muscleGroupsIds != null && muscleGroupsIds.size() > 0) {
            StringBuilder sb = new StringBuilder();
            String separator = " + ";
            for (Integer id : muscleGroupsIds) {
                sb.append(mMuscleGroupsNameArray[id]);
                sb.append(separator);
            }
            defaultTitle =  sb.substring(0, sb.length() - separator.length());
        }
        else {
            defaultTitle = getResources().getString(R.string.default_workout_title);
        }
        mDiaryEntryViewModel.setDefaultTitle(defaultTitle);
    }

    private void updateDuration() {
        FullDiaryEntry diaryEntry = mDiaryEntryViewModel.getEntry().get();
        if (diaryEntry.getStartTime() != null && diaryEntry.getFinishTime() != null) {
            Time startTime = diaryEntry.getStartTime();
            Time finishTime = diaryEntry.getFinishTime();
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
        ArrayList<Integer> selectedMuscleGroupsIdList = mDiaryEntryViewModel.getEntry().get().getMuscleGroupsIds();

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

            updateDefaultTitle();
        }
    }
}