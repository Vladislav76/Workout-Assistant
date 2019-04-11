package com.vladislav.workoutassistant.ui.workouts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import com.vladislav.workoutassistant.R
import com.vladislav.workoutassistant.ui.main.GeneralFragment
import com.vladislav.workoutassistant.ui.main.interfaces.OnItemClickCallback
import com.vladislav.workoutassistant.data.models.WorkoutExercise
import com.vladislav.workoutassistant.data.models.WorkoutProgram
import com.vladislav.workoutassistant.data.models.WorkoutSet
import com.vladislav.workoutassistant.ui.exercises.ExercisesFragment
import com.vladislav.workoutassistant.ui.workouts.adapters.SetAndExerciseAdapter
import com.vladislav.workoutassistant.ui.main.components.DividerItemDecoration
import com.vladislav.workoutassistant.ui.main.components.SimpleItemTouchHelperCallback
import com.vladislav.workoutassistant.ui.workouts.viewmodels.WorkoutViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class WorkoutDetailsFragment : GeneralFragment(), SimpleItemTouchHelperCallback.OnStartDragListener {

    private val mWorkoutViewModel: WorkoutViewModel by lazy {
        ViewModelProviders.of(this).get(WorkoutViewModel::class.java)
    }
    private var mProgramRecyclerView: RecyclerView? = null
    private var mAdapter: SetAndExerciseAdapter? = null
    private var mItemTouchHelper: ItemTouchHelper? = null

    private val mCallback = object : OnItemClickCallback {
        override fun onClick(id: Int, name: String) {
            //TODO: some actions
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_workout_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mFragmentListener!!.updateToolbarTitle(arguments!!.getString(TITLE_ARG)!!)
        setHasOptionsMenu(true)

        mProgramRecyclerView = view.findViewById(R.id.recycler_view)

        mAdapter = SetAndExerciseAdapter(mCallback, this, mWorkoutViewModel.muscleGroups)
        mWorkoutViewModel.setsAndExercisesList.observe(this, Observer { list ->
            if (list != null) {
                mAdapter!!.updateList(list)
                mAdapter!!.notifyDataSetChanged()
            }
        })
        mWorkoutViewModel.init(arguments!!.getInt(WORKOUT_ID_ARG))
        mProgramRecyclerView!!.adapter = mAdapter
        mProgramRecyclerView!!.addItemDecoration(DividerItemDecoration(activity, R.drawable.divider, 20))

//        experimental
        mWorkoutViewModel.workoutInfo.observe(this, Observer { info ->
            println(info.workout.toString())
            println(info.sets.toString())
            for (set in info.sets) {
                for (ex in set.exercises) {
                    println("Exercise name: ${ex.name} muscleGroupId: ${ex.muscleGroupId}")
                }
            }
        })
        mWorkoutViewModel.loadWorkoutInfoById(arguments!!.getInt(WORKOUT_ID_ARG))
        //end of experimental


        mItemTouchHelper = ItemTouchHelper(SimpleItemTouchHelperCallback(mAdapter))
        mItemTouchHelper!!.attachToRecyclerView(mProgramRecyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.workouts_actions, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.add_exercises_action -> {
                mFragmentListener!!.addFragmentOnTop(ExercisesFragment.newInstance(true))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        mItemTouchHelper!!.startDrag(viewHolder)
    }

    companion object {

        private val WORKOUT_ID_ARG = "workout_id_arg"
        private val TITLE_ARG = "title_arg"

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
