package com.vladislav.workoutassistant.data;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class DiaryEntry implements Cloneable {

    public DiaryEntry(Date date, Date startTime, Date finishTime, String title) {
        mId = Diary.getNextDiaryEntryIdAndIncrement();
        mDate = date;
        mStartTime = startTime;
        mFinishTime = finishTime;
        mTitle = title;
        mDuration = new Time(mFinishTime.getTime() - mStartTime.getTime());
        mMuscleGroupsIds = new ArrayList<>();
    }

    public DiaryEntry() {
        mId = Diary.NEW_TEMP_DIARY_ENTRY;
        mDate = new Date();
        mMuscleGroupsIds = new ArrayList<>();
    }

    public int getId() {
        return mId;
    }

    public Date getDate() {
        return mDate;
    }

    public Date getStartTime() {
        return mStartTime;
    }

    public Date getFinishTime() {
        return mFinishTime;
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getDuration() {
        return mDuration;
    }

    public ArrayList<Integer> getMuscleGroupsIds() {
        return mMuscleGroupsIds;
    }

    public void setId(int id) {
        mId = id;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setStartTime(Date startTime) {
        mStartTime = startTime;
    }

    public void setFinishTime(Date finishTime) {
        mFinishTime = finishTime;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDuration(Date duration) {
        mDuration = duration;
    }

    public void setMuscleGroupsIds(ArrayList<Integer> muscleGroups) {
        mMuscleGroupsIds = muscleGroups;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        DiaryEntry clonedEntry = (DiaryEntry) super.clone();
        clonedEntry.mDate = (Date) mDate.clone();
        clonedEntry.mStartTime = (Date) mStartTime.clone();
        clonedEntry.mFinishTime = (Date) mFinishTime.clone();
        clonedEntry.mDuration = (Date) mDuration.clone();
        clonedEntry.mMuscleGroupsIds = (ArrayList<Integer>) mMuscleGroupsIds.clone();
        return clonedEntry;
    }

    private int mId;
    private Date mDate;
    private Date mStartTime;
    private Date mFinishTime;
    private Date mDuration;
    private String mTitle;
    private ArrayList<Integer> mMuscleGroupsIds;
}
