package com.vladislav.workoutassistant.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladislav.workoutassistant.data.models.Identifiable

@Entity(tableName = "exercises")
data class Exercise(@PrimaryKey(autoGenerate = true) override val id: Int = 0,
                    val name: String,
                    val description: String,
                    @ColumnInfo(name = "muscle_group_id") val muscleGroupId: Int) : Identifiable