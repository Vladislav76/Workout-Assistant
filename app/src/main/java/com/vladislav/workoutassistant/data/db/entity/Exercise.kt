package com.vladislav.workoutassistant.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladislav.workoutassistant.data.models.Identifiable
import com.vladislav.workoutassistant.data.models.Nameable

@Entity(tableName = "exercises")
data class Exercise(@PrimaryKey(autoGenerate = true) override val id: Int = 0,
                    override var name: String,
                    val description: String,
                    @ColumnInfo(name = "muscle_group_id") val muscleGroupId: Int) : Identifiable, Nameable