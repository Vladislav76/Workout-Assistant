package com.vladislav.workoutassistant.data.models

import com.vladislav.workoutassistant.data.db.entity.Workout

import java.util.ArrayList

class WorkoutGroup(private val mData: Item) {

    var workouts: List<Workout> = listOf()

    val groupId: Int
        get() = mData.id

    val name: String
        get() = mData.name
}
