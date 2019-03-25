package com.vladislav.workoutassistant.data.db.entity;

import com.vladislav.workoutassistant.data.model.FullDiaryEntry;
import com.vladislav.workoutassistant.data.model.Identifiable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "diary")
public class DiaryEntry implements FullDiaryEntry, Identifiable {

    @Ignore
    public DiaryEntry() {
        mMuscleGroupsIds = new ArrayList<>();
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "date")
    private Date mDate;

    @ColumnInfo(name = "duration")
    private Date mDuration;

    @ColumnInfo(name = "start_time")
    private Time mStartTime;

    @ColumnInfo(name = "finish_time")
    private Time mFinishTime;

    @ColumnInfo(name = "muscle_groups_ids")
    private ArrayList<Integer> mMuscleGroupsIds;

    @Ignore
    private boolean mIsSelected;

    @Ignore
    private boolean mIsDefaultTitleChecked;

    @Ignore
    private String mDefaultTitle;

    public DiaryEntry(Date date, Time startTime, Time finishTime, String title) {
        mDate = date;
        mStartTime = startTime;
        mFinishTime = finishTime;
        mTitle = title;
        mDuration = new Time(finishTime.getTime() - startTime.getTime());
        mMuscleGroupsIds = new ArrayList<>();
    }

    /* GETTERS */
    @Override
    public int getId() {
        return mId;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public Date getDate() {
        return mDate;
    }

    @Override
    public Date getDuration() {
        return mDuration;
    }

    @Override
    public boolean isSelected() {
        return mIsSelected;
    }

    @Override
    public boolean isDefaultTitleChecked() {
        return mIsDefaultTitleChecked;
    }

    @Override
    public Time getStartTime() {
        return mStartTime;
    }

    @Override
    public Time getFinishTime() {
        return mFinishTime;
    }

    @Override
    public String getDefaultTitle() {
        return mDefaultTitle;
    }

    @Override
    public ArrayList<Integer> getMuscleGroupsIds() {
        return mMuscleGroupsIds;
    }

    /* SETTERS */
    public void setId(int id) {
        mId = id;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setSelected(boolean selected) {
        mIsSelected = selected;
    }

    public void setStartTime(Time time) {
        mStartTime = time;
    }

    public void setFinishTime(Time time) {
        mFinishTime = time;
    }

    public void setDuration(Date duration) {
        mDuration = duration;
    }

    public void setDefaultTitle(String title) {
        mDefaultTitle = title;
    }

    public void setDefaultTitleChecked(boolean isChecked) {
        mIsDefaultTitleChecked = isChecked;
    }

    public void setMuscleGroupsIds(ArrayList<Integer> ids) {
        mMuscleGroupsIds = ids;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        DiaryEntry entry = (DiaryEntry) other;
        return mId == entry.getId() &&
                mIsSelected == entry.isSelected() &&
                mDate.equals(entry.getDate()) &&
                mTitle.equals(entry.getTitle()) &&
                mDuration.equals(entry.getDuration());
    }
}
