package com.vladislavmyasnikov.feature_workout_library_impl.domain

import com.vladislavmyasnikov.common.interfaces.HasList

class FullWorkout(
        _id: Long,
        _title: String,
        _avatarID: String,
        val description: String,
        val workoutSets: List<WorkoutSet>
) : ShortWorkout(_id, _title, _avatarID), HasList<WorkoutSet> {

    override val list: List<WorkoutSet> = workoutSets
}