package com.vladislav.workoutassistant.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.vladislav.workoutassistant.data.db.entity.Set

@Dao
interface SetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSets(sets: List<Set>)
}
