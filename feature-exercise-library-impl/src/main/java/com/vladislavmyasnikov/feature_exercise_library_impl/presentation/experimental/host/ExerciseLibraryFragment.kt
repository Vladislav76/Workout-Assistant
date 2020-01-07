package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.host

import com.vladislavmyasnikov.common.experimental.view.BaseFragment
import com.vladislavmyasnikov.common.experimental.view.HostFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.content.ExerciseListFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.content.ExerciseListToolbarFragment

class ExerciseLibraryFragment : HostFragment(R.layout.two_fragment_container) {

    companion object {
        fun newInstance() = ExerciseLibraryFragment()
    }

    override val label = "exercise_library_fragment"

    override val children: List<Pair<Int,BaseFragment>> = listOf(
            R.id.top_container to ExerciseListToolbarFragment.newInstance(),
            R.id.bottom_container to ExerciseListFragment.newInstance()
    )
}