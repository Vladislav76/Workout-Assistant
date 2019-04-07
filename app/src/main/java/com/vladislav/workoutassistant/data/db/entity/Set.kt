package com.vladislav.workoutassistant.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladislav.workoutassistant.data.models.Identifiable

@Entity(tableName = "sets")
data class Set(@PrimaryKey(autoGenerate = true) override val id: Int = 0,
               @ColumnInfo(name = "workout_id") val workoutId: Int,
               var amount: Int) : Identifiable