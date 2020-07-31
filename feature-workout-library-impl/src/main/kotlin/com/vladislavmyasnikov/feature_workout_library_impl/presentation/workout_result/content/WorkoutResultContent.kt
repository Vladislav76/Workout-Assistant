package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_result.content

import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.viewmodel.BaseVM
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.VMFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_result.viewmodel.WorkoutResultVM
import kotlinx.android.synthetic.main.content_workout_result.*
import javax.inject.Inject

class WorkoutResultContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<Unit>(R.layout.content_workout_result) {

    override val viewModel: BaseVM<Unit, Throwable> by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutResultVM::class.java)
    }

    override fun onReceiveItem(item: Unit) {
        save_button.isEnabled = false
    }
}