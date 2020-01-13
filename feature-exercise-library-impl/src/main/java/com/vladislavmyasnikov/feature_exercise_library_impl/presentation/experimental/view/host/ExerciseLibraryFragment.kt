package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.view.host

import com.vladislavmyasnikov.common.experimental.view.HostFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.view.content.ExerciseListFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.view.content.ExerciseListToolbarFragment

class ExerciseLibraryFragment : HostFragment(R.layout.two_fragment_container) {

    companion object {
        fun newInstance() = ExerciseLibraryFragment()
    }

    override val label = "exercise_library_fragment"

    override val children = listOf(
            R.id.top_container to ExerciseListToolbarFragment::class.java,
            R.id.bottom_container to ExerciseListFragment::class.java
    )
}