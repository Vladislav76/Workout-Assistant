package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.content

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.vladislavmyasnikov.common.experimental.view.components.ListFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.di.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ShortExerciseInfo
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.ExerciseAdapter
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.ExerciseListViewModel
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class ExerciseListFragment : ListFragment<ShortExerciseInfo>() {

    companion object {
        fun newInstance() = ExerciseListFragment()
    }

    override val label = "exercise_list_fragment"

    @Inject
    override lateinit var adapter: ExerciseAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override lateinit var viewModel: ExerciseListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ExerciseLibraryFeatureComponent.get().exerciseListScreenComponent.getValue().inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExerciseListViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetch()
    }
}