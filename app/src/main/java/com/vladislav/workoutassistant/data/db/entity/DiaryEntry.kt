package com.vladislav.workoutassistant.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.vladislav.workoutassistant.data.models.Identifiable
import com.vladislav.workoutassistant.data.models.Nameable
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "diary")
data class DiaryEntry(@PrimaryKey(autoGenerate = true) override var id: Int = 0,  //TODO: var -> val
                      override var name: String = "",
                      var date: Date = Date(),
                      var duration: Date = Date(0),
                      @ColumnInfo(name = "start_time") var startTime: Time? = null,
                      @ColumnInfo(name = "finish_time") var finishTime: Time? = null,
                      @ColumnInfo(name = "muscle_groups_ids") var muscleGroupsIds: List<Int> = ArrayList(),
                      @Ignore var isSelected: Boolean = false,
                      @Ignore var isDefaultTitleChecked: Boolean = false,
                      @Ignore var defaultTitle: String = "") : Identifiable, Nameable