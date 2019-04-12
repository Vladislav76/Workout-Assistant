package com.vladislav.workoutassistant.ui.workouts

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.vladislav.workoutassistant.R
import com.vladislav.workoutassistant.ui.exercises.ExercisesFragment
import com.vladislav.workoutassistant.ui.main.GeneralFragment
import com.vladislav.workoutassistant.ui.main.components.DividerItemDecoration
import com.vladislav.workoutassistant.ui.main.interfaces.OnItemClickCallback
import com.vladislav.workoutassistant.ui.workouts.adapters.WorkoutExerciseAdapter
import com.vladislav.workoutassistant.ui.workouts.viewmodels.WorkoutViewModel

class WorkoutDetailsFragment : GeneralFragment() {

    private val mWorkoutViewModel: WorkoutViewModel by lazy {
        ViewModelProviders.of(this).get(WorkoutViewModel::class.java)
    }
    private val mCallback = object : OnItemClickCallback {
        override fun onClick(id: Int, name: String) {
            Toast.makeText(activity, "$name $id", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_workout_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mFragmentListener!!.updateToolbarTitle(arguments!!.getString(TITLE_ARG)!!)
        setHasOptionsMenu(true)

        val mAdapter = WorkoutExerciseAdapter(activity!!.application, mCallback)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(activity, R.drawable.divider, 20))

        mWorkoutViewModel.workoutInfo.observe(this, Observer { info ->
            mAdapter.updateContent(info.sets)
        })
        mWorkoutViewModel.loadWorkoutInfoById(arguments!!.getInt(WORKOUT_ID_ARG))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.workouts_actions, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_exercises_action -> {
                mFragmentListener?.addFragmentOnTop(ExercisesFragment.newInstance(true))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    companion object {

        private const val WORKOUT_ID_ARG = "workout_id_arg"
        private const val TITLE_ARG = "title_arg"

        fun newInstance(id: Int, title: String): WorkoutDetailsFragment {
            val args = Bundle()
            args.putInt(WORKOUT_ID_ARG, id)
            args.putString(TITLE_ARG, title)

            val fragment = WorkoutDetailsFragment()
            fragment.arguments = args

            return fragment
        }
    }
}