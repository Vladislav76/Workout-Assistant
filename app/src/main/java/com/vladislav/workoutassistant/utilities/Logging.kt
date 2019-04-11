package com.vladislav.workoutassistant.utilities

import android.util.Log
import com.vladislav.workoutassistant.data.models.WorkoutProgram

object Logging {

    fun printWorkoutProgram(workoutProgram: WorkoutProgram) {
        val sb = StringBuilder()
        sb.append("\n* * * * * W O R K O U T * * * * *\n")
        sb.append("category ID #")
        sb.append(workoutProgram.workoutInfo!!.categoryId)
        sb.append("\n")
        for (workoutSet in workoutProgram.workoutSets!!) {
            sb.append("\n* * * * * S E T * * * * *\n")
            sb.append("\t* set ID #")
            sb.append(workoutSet.setInfo?.id)
            sb.append("\n\t* amount #")
            sb.append(workoutSet.setInfo?.amount)
            sb.append("\n")
            for (workoutExercise in workoutSet.workoutExercises!!) {
                sb.append("\n\t\t* exercise ID #")
                sb.append(workoutExercise.matchingInfo?.exerciseId)
                sb.append("\n\t\t* ")
                sb.append(workoutExercise.exerciseInfo?.name)
                sb.append("; ")
                sb.append(workoutExercise.exerciseInfo?.description)
                sb.append("\n\t\t* amount #")
                sb.append(workoutExercise.matchingInfo?.amount)
                sb.append("\n")
            }
        }
        Log.d("Workout Program Info", sb.toString())
    }
}
