package com.vladislav.workoutassistant.ui.exercises

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vladislav.workoutassistant.R
import com.vladislav.workoutassistant.ui.main.GeneralFragment
import com.vladislav.workoutassistant.ui.main.interfaces.OnItemClickCallback
import com.vladislav.workoutassistant.ui.main.components.CustomItemDecoration
import com.vladislav.workoutassistant.ui.main.dialogs.ExerciseDetailsFragment
import com.vladislav.workoutassistant.data.db.entity.Exercise
import com.vladislav.workoutassistant.ui.exercises.adapters.ExerciseAdapter
import com.vladislav.workoutassistant.ui.exercises.viewmodels.ExerciseListViewModel
import com.vladislav.workoutassistant.ui.workouts.adapters.CategoryAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView

class ExercisesFragment : GeneralFragment() {

    private var mExerciseListViewModel: ExerciseListViewModel? = null
    private var mExerciseAdapter: ExerciseAdapter? = null
    private var mMuscleGroupAdapter: CategoryAdapter? = null
    private var mCurrentMuscleGroupId: Int = 0

    private val mExerciseClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Int, name: String) {
            showDialog(id)
        }
    }

    private val mMuscleGroupClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Int, name: String) {
            if (id != mCurrentMuscleGroupId) {
                mCurrentMuscleGroupId = id
                mExerciseListViewModel!!.init(id)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_horizontal_and_vertical_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mFragmentListener!!.updateToolbarTitle(R.string.exercises_tab)

        mExerciseListViewModel = ViewModelProviders.of(this).get(ExerciseListViewModel::class.java)

        val muscleGroupsRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        mMuscleGroupAdapter = CategoryAdapter(mExerciseListViewModel!!.getMuscleGroups(), mMuscleGroupClickCallback)
        muscleGroupsRecyclerView.adapter = mMuscleGroupAdapter
        muscleGroupsRecyclerView.addItemDecoration(CustomItemDecoration(10))

        val exercisesRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        mExerciseAdapter = ExerciseAdapter(mExerciseClickCallback, mExerciseListViewModel!!.getSelectedExercises())
        exercisesRecyclerView.adapter = mExerciseAdapter
        mExerciseListViewModel!!.exercises.observe(this, Observer { exercises ->
            if (exercises != null) {
                mExerciseAdapter!!.updateList(exercises)
            }
        })

        if (savedInstanceState != null) {
            mCurrentMuscleGroupId = savedInstanceState.getInt(LAST_SELECTED_MUSCLE_GROUP_ID)
            mMuscleGroupAdapter!!.setItemPosition(mCurrentMuscleGroupId)
        }
        mExerciseListViewModel!!.init(mCurrentMuscleGroupId)
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt(LAST_SELECTED_MUSCLE_GROUP_ID, mCurrentMuscleGroupId)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (intent != null) {
            when (requestCode) {
                SHOW_EXERCISE_INFO_REQUEST_CODE ->
                    if (resultCode == Activity.RESULT_OK) {
                        val exerciseId = intent.getIntExtra(ExerciseDetailsFragment.EXERCISE_ID_DATA, -1)
                        Log.d("FROM_DIALOG_EX_ID:", exerciseId.toString())
                    }

                GET_COUNT_DATA_REQUEST_CODE -> when (resultCode) {
                    Activity.RESULT_OK -> {
                        val exerciseId = intent.getIntExtra(ExerciseDetailsFragment.EXERCISE_ID_DATA, -1)
                        val exerciseCount = intent.getIntExtra(ExerciseDetailsFragment.EXERCISE_COUNT_DATA, -1)

                        mExerciseListViewModel!!.selectExercise(exerciseId, exerciseCount)
                        mExerciseAdapter!!.updateLastSelectedItem()

                        Log.d("FROM_DIALOG_EX_ID:", exerciseId.toString())
                        Log.d("FROM_DIALOG_EX_COUNT:", exerciseCount.toString())
                    }
                    Activity.RESULT_CANCELED -> {
                        val exerciseId = intent.getIntExtra(ExerciseDetailsFragment.EXERCISE_ID_DATA, -1)

                        mExerciseListViewModel!!.unselectExercise(exerciseId)
                        mExerciseAdapter!!.updateLastSelectedItem()

                        Log.d("FROM_DIALOG_EX_ID:", exerciseId.toString())
                    }
                }
            }
        }
    }

    private fun showDialog(exerciseId: Int) {
        val dialog: DialogFragment

        if (arguments!!.getBoolean(MULTIPLE_SELECTION_MODE_ENABLED_ARG)) {
            dialog = ExerciseDetailsFragment.newInstance(exerciseId, mExerciseListViewModel!!.getExerciseAmount(exerciseId, 0))
            dialog.setTargetFragment(this, GET_COUNT_DATA_REQUEST_CODE)
        } else {
            dialog = ExerciseDetailsFragment.newInstance(exerciseId)
            dialog.setTargetFragment(this, SHOW_EXERCISE_INFO_REQUEST_CODE)
        }
        dialog.show(fragmentManager!!, null)
    }



    companion object {

        private const val LAST_SELECTED_MUSCLE_GROUP_ID = "last_selected_muscle_group_id"
        private const val MULTIPLE_SELECTION_MODE_ENABLED_ARG = "multiple_selection_mode_enabled_arg"

        private const val SHOW_EXERCISE_INFO_REQUEST_CODE = 1
        private const val GET_COUNT_DATA_REQUEST_CODE = 2

        fun newInstance(multipleSelectionModeEnabled: Boolean): ExercisesFragment {
            val args = Bundle()
            args.putBoolean(MULTIPLE_SELECTION_MODE_ENABLED_ARG, multipleSelectionModeEnabled)

            val fragment = ExercisesFragment()
            fragment.arguments = args

            return fragment
        }
    }
}
