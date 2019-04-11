package com.vladislav.workoutassistant.data.models

import androidx.room.*
import com.vladislav.workoutassistant.data.db.entity.Set
import com.vladislav.workoutassistant.data.db.entity.SetVsExerciseMatching
import com.vladislav.workoutassistant.data.db.entity.Workout

data class WorkoutInfo(@Embedded var workout: Workout,
                       @Relation(parentColumn = "id", entityColumn = "workout_id", entity = Set::class)
                       var sets: List<SetInfo>)

data class SetInfo(@ColumnInfo(name = "id") var id: Int,
                   @ColumnInfo(name = "amount") var amount: Int,
                   @Relation(parentColumn = "id", entityColumn = "set_id", entity = SetVsExerciseMatching::class)
                   var exercises: List<ExerciseInfo>)

data class ExerciseInfo(@ColumnInfo(name = "exercise_id") var id: Int,
                        @ColumnInfo(name = "amount") var amount: Int,
                        @ColumnInfo(name = "id") var entryId: Int) {

    @Ignore
    var muscleGroupId: Int = 0

    @Ignore
    var name: String = ""
}

data class ExerciseContent(@ColumnInfo(name = "id") val id:Int,
                           @ColumnInfo(name = "name") var name: String,
                           @ColumnInfo(name = "muscle_group_id") var muscleGroupId: Int)