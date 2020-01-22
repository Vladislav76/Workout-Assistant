package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.HostFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.di.FeatureComponent
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

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = FeatureComponent.get().exerciseDetailsComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        FeatureComponent.get().exerciseDetailsComponent.resetValue()
        return super.onBackPressed()
    }
}