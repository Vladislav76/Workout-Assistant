package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.host

import com.vladislavmyasnikov.common.experimental.SharedBus
import com.vladislavmyasnikov.common.experimental.view.HostFragment
import com.vladislavmyasnikov.common.interfaces.OnBackPressedListener
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.content.ExerciseFragment
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ExerciseDetailsFragment @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : HostFragment(R.layout.fragment_container) {

    override val label = "exercise_details_fragment"

    override val children = listOf(
            R.id.container to ExerciseFragment::class.java
    )
}