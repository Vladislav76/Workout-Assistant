package com.vladislav.workoutassistant.data;

import java.sql.Time;
import java.util.Date;

public class DiaryEntry {

    public DiaryEntry(int id, Date date, Date startTime, Date finishTime, String title, String[] muscleGroups) {
        mId = id;
        mDate = date;
        mStartTime = startTime;
        mFinishTime = finishTime;
        mTitle = title;
        mMuscleGroups = muscleGroups;
        mDuration = new Time(mFinishTime.getTime() - mStartTime.getTime());
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

    public String[] getMuscleGroups() {
        return mMuscleGroups;
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

    public void setMuscleGroups(String[] muscleGroups) {
        mMuscleGroups = muscleGroups;
    }

    private int mId;
    private Date mDate;
    private Date mStartTime;
    private Date mFinishTime;
    private Date mDuration;
    private String mTitle;
    private String[] mMuscleGroups;
}
