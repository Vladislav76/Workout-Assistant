package com.vladislav.workoutassistant.data.db.entity;

import com.vladislav.workoutassistant.data.model.FullDiaryEntry;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "diary")
public class DiaryEntryEntity implements FullDiaryEntry {

    public DiaryEntryEntity(Date date, Time startTime, Time finishTime, String title) {
        this.date = date;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.title = title;
        this.duration = new Time(finishTime.getTime() - startTime.getTime());
        this.muscleGroupsIds = new ArrayList<>();
    }

    @Ignore
    public DiaryEntryEntity() {
        this.muscleGroupsIds = new ArrayList<>();
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public String title;

    @ColumnInfo
    public Date date;

    @ColumnInfo
    public Date duration;

    @ColumnInfo(name = "start_time")
    public Time startTime;

    @ColumnInfo(name = "finish_time")
    public Time finishTime;

    @ColumnInfo(name = "muscle_groups_ids")
    public ArrayList<Integer> muscleGroupsIds;

    @Ignore
    private boolean selected;

    @Ignore
    private boolean isDefaultTitleChecked;

    @Ignore
    private String defaultTitle;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public Date getDuration() {
        return duration;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public boolean isDefaultTitleChecked() {
        return isDefaultTitleChecked;
    }

    @Override
    public Time getStartTime() {
        return startTime;
    }

    @Override
    public Time getFinishTime() {
        return finishTime;
    }

    @Override
    public String getDefaultTitle() {
        return defaultTitle;
    }

    @Override
    public ArrayList<Integer> getMuscleGroupsIds() {
        return muscleGroupsIds;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setStartTime(Time time) {
        startTime = time;
    }

    public void setFinishTime(Time time) {
        finishTime = time;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public void setDefaultTitle(String title) {
        defaultTitle = title;
    }

    public void setDefaultTitleChecked(boolean isChecked) {
        isDefaultTitleChecked = isChecked;
    }

    public void setMuscleGroupsIds(ArrayList<Integer> ids) {
        muscleGroupsIds = ids;
    }
}
