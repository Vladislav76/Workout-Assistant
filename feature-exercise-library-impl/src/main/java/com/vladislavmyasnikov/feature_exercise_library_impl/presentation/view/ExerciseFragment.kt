package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.vladislavmyasnikov.core_components.components.GeneralViewModel
import com.vladislavmyasnikov.core_components.view.GeneralFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.di.AdapterModule
import com.vladislavmyasnikov.feature_exercise_library_impl.di.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.adapters.ExerciseImagePagerAdapter
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels.ExerciseViewModel
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_exercise.*
import javax.inject.Inject

class ExerciseFragment : GeneralFragment<ExerciseViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: ExerciseImagePagerAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        ExerciseLibraryFeatureComponent.get().exerciseLibraryScreenComponent(AdapterModule(activity!!)).inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExerciseViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // screenTitleController.setTitle(arguments!!.getString(TITLE_ARG)!!)
        screenTitleController.setTitle("")
        screenTitleController.setDisplayHomeAsUpEnabled(true)

        view_pager.adapter = adapter

        if (savedInstanceState == null) {
            viewModel.fetchFullExerciseInfo(arguments!!.getLong(EXERCISE_ID_ARG))
        }
    }

    override fun <Int> onReceiveItem(item: Int) {
        when (item) {
            GeneralViewModel.LOADED_REQUEST_RESULT -> { updateContent() }
        }
    }

    private fun updateContent() {
        val exerciseInfo = viewModel.exerciseInfo
        title_field.text = exerciseInfo.title
        description_field.text = exerciseInfo.description
        adapter.imagesIDs = exerciseInfo.imagesIDs
    }

    companion object {
        private const val EXERCISE_ID_ARG = "exercise_id_arg"
        private const val TITLE_ARG = "title_arg"

        fun newInstance(id: Long, title: String): ExerciseFragment {
            return ExerciseFragment().apply {
                arguments = Bundle().apply {
                    putLong(EXERCISE_ID_ARG, id)
                    putString(TITLE_ARG, title)
                }
            }
        }
    }
}