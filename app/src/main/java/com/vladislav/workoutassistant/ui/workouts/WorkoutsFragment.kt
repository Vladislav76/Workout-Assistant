package com.vladislav.workoutassistant.ui.workouts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.vladislav.workoutassistant.R
import com.vladislav.workoutassistant.ui.main.GeneralFragment
import com.vladislav.workoutassistant.ui.main.interfaces.OnItemClickCallback
import com.vladislav.workoutassistant.ui.main.components.CustomItemDecoration
import com.vladislav.workoutassistant.data.models.WorkoutGroup
import com.vladislav.workoutassistant.ui.workouts.adapters.CategoryAdapter
import com.vladislav.workoutassistant.ui.workouts.adapters.WorkoutGroupAdapter
import com.vladislav.workoutassistant.ui.workouts.viewmodels.WorkoutGroupListViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WorkoutsFragment : GeneralFragment() {

    private var mWorkoutsCardsRecyclerView: RecyclerView? = null
    private var mWorkoutGroupAdapter: WorkoutGroupAdapter? = null
    private var mWorkoutGroupListViewModel: WorkoutGroupListViewModel? = null
    private var mCurrentCategoryId: Int = 0

    private val mCategoryClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Int, name: String) {
            if (id != mCurrentCategoryId) {
                mCurrentCategoryId = id
                mWorkoutGroupListViewModel!!.init(id)
            }
        }
    }
    private val mWorkoutGroupClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Int, name: String) {
            Toast.makeText(activity, "$name, id:$id", Toast.LENGTH_SHORT).show()
        }
    }
    private val mWorkoutClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Int, name: String) {
            mFragmentListener!!.addFragmentOnTop(WorkoutDetailsFragment.newInstance(id, name))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_horizontal_and_vertical_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mFragmentListener!!.updateToolbarTitle(R.string.workouts_tab)

        mWorkoutGroupListViewModel = ViewModelProviders.of(this).get(WorkoutGroupListViewModel::class.java)

        val categoriesRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        val categoryAdapter = CategoryAdapter(mWorkoutGroupListViewModel!!.categories, mCategoryClickCallback)
        categoriesRecyclerView.adapter = categoryAdapter
        categoriesRecyclerView.addItemDecoration(CustomItemDecoration(10))

        mWorkoutsCardsRecyclerView = view.findViewById(R.id.vertical_recycler_view)
        mWorkoutGroupAdapter = WorkoutGroupAdapter(mWorkoutGroupClickCallback, mWorkoutClickCallback)
        mWorkoutsCardsRecyclerView!!.adapter = mWorkoutGroupAdapter
        mWorkoutGroupListViewModel!!.workoutGroups.observe(this, Observer { workoutGroups ->
            if (workoutGroups != null) {
                mWorkoutGroupAdapter!!.setList(workoutGroups)
                mWorkoutGroupAdapter!!.notifyDataSetChanged()
                mWorkoutsCardsRecyclerView!!.scrollToPosition(0)
                (categoriesRecyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(mCurrentCategoryId, 0)
            }
        })

        if (savedInstanceState != null) {
            Log.d("WORKOUTS", "Current category id: $mCurrentCategoryId")
            mCurrentCategoryId = savedInstanceState.getInt(CURRENT_CATEGORY_ID)
            categoryAdapter.setItemPosition(mCurrentCategoryId)
        }
        mWorkoutGroupListViewModel!!.init(mCurrentCategoryId)
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt(CURRENT_CATEGORY_ID, mCurrentCategoryId)
    }

    companion object {

        private val CURRENT_CATEGORY_ID = "current_category_id"

        fun newInstance(): WorkoutsFragment {
            return WorkoutsFragment()
        }
    }
}