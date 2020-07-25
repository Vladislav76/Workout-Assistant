package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutSetConfig
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller.ChangeWorkoutSetConfigUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller.GetWorkoutSetConfigUC
import io.reactivex.Completable
import javax.inject.Inject

class WorkoutSetConfigVM @Inject constructor(
        private val getWorkoutSetConfigUC: GetWorkoutSetConfigUC,
        private val changeWorkoutSetConfigUC: ChangeWorkoutSetConfigUC
) : SimpleVM<WorkoutSetConfig>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(getWorkoutSetConfigUC()))
    }

    fun updateWorkoutSetNumber(number: Int) {
        changeWorkoutSetConfigUC.putSetIndex(number)
    }

    fun updateWorkoutSetApproach(approach: Int) {
        changeWorkoutSetConfigUC.putApproachIndex(approach)
    }
}