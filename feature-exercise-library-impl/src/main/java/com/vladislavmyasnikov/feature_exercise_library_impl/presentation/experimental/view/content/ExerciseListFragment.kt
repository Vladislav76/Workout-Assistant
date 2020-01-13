package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.view.content

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.experimental.view.components.ListFragment
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ShortExerciseInfo
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.adapters.ExerciseAdapter
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.viewmodels.ExerciseListViewModel
import javax.inject.Inject

class ExerciseListFragment @Inject constructor(
        override val adapter: ExerciseAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
) : ListFragment<ShortExerciseInfo>() {

    override val label = "exercise_list_fragment"

    override lateinit var viewModel: ExerciseListViewModel

    override val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            debugMessage("Item is clicked: id=$id, title=$title")
            //router.navigateTo(Screens.ExerciseDetailsScreen(id, title))
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ExerciseListViewModel::class.java)
        debugMessage(viewModel.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetch()
    }
}

/*
    override fun onBackPressed(): Boolean {
        ExerciseLibraryFeatureComponent.get().exerciseListScreenComponent.resetValue()
        return super.onBackPressed()
    }
 */