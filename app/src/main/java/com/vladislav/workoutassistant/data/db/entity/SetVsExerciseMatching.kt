package com.vladislav.workoutassistant.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "sets_vs_exercises",
        foreignKeys = [
            ForeignKey(entity = Set::class, parentColumns = ["id"], childColumns = ["set_id"], onDelete = CASCADE),
            ForeignKey(entity = Exercise::class, parentColumns = ["id"], childColumns = ["exercise_id"], onDelete = CASCADE)
        ])
class SetVsExerciseMatching(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                            @ColumnInfo(name = "set_id", index = true) val setId: Int,
                            @ColumnInfo(name = "exercise_id", index = true) val exerciseId: Int,
                            var amount: Int)
