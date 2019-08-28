package com.vladislavmyasnikov.feature_workout_library_impl.data.db.models

import androidx.room.Embedded
import androidx.room.Relation
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.SetInfo
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutInfo

class FullWorkoutInfo(
        @Embedded val workout: WorkoutInfo,
        @Relation(entity = SetInfo::class, parentColumn = "sets_ids", entityColumn = "id")
        val sets: List<SetInfo>
)