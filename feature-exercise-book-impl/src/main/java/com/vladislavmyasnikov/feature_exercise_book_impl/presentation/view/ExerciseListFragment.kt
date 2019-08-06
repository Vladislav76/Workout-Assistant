package com.vladislavmyasnikov.feature_exercise_book_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.core_components.components.GeneralViewModel
import com.vladislavmyasnikov.core_components.interfaces.FragmentController
import com.vladislavmyasnikov.core_components.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.core_components.interfaces.ScreenTitleController
import com.vladislavmyasnikov.core_utils.utils.utils.Logger
import com.vladislavmyasnikov.feature_exercise_book_impl.R
import com.vladislavmyasnikov.feature_exercise_book_impl.di.ExerciseBookFeatureComponent
import com.vladislavmyasnikov.feature_exercise_book_impl.presentation.adapters.ExerciseAdapter
import com.vladislavmyasnikov.feature_exercise_book_impl.presentation.viewmodels.ExerciseListViewModel
import com.vladislavmyasnikov.feature_exercise_book_impl.presentation.viewmodels.ViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ExerciseListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: ExerciseAdapter

    @Inject
    lateinit var screenTitleController: ScreenTitleController

    @Inject
    lateinit var fragmentController: FragmentController

    private lateinit var viewModel: ExerciseListViewModel
    private val disposables = CompositeDisposable()

    private val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        ExerciseBookFeatureComponent.get().inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExerciseListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateTitle()
        screenTitleController.setDisplayHomeAsUpEnabled(false)

        view.findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter

        disposables.add(viewModel.loadingState
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Logger.debug(TAG, "Exercises fetching: LOADING IS ${if (it) "STARTED" else "COMPLETED"}")
                })

        disposables.add(viewModel.errors
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Logger.debug(TAG, "Exercises fetching: ERROR; Cause: $it")
                })

        disposables.add(viewModel.items
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    when (it) {
                        GeneralViewModel.LOADED_REQUEST_RESULT -> {
                            adapter.updateList(viewModel.exercisesInfo)
                            Logger.debug(TAG, "Exercises fetching: SUCCESS; Amount: ${viewModel.exercisesInfo.size}")
                        }
                    }
                })

        if (savedInstanceState == null) {
            adapter.callback = itemClickCallback

            viewModel.fetchShortExercisesInfo()
            Logger.debug(this.toString(), "Exercises fetching: REQUEST")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    private fun updateTitle() {
        screenTitleController.setTitle(R.string.exercise_list_screen_title)
    }

    companion object {
        private const val TAG = "EXERCISE_LIST_FRAGMENT"

        fun newInstance() = ExerciseListFragment()
    }
}
