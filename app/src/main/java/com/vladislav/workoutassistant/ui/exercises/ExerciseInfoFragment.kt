package com.vladislav.workoutassistant.ui.exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vladislav.workoutassistant.R
import com.vladislav.workoutassistant.databinding.FragmentExerciseInfoBinding
import com.vladislav.workoutassistant.ui.exercises.viewmodels.ExerciseViewModel

class ExerciseInfoFragment : Fragment() {

    private var mBinding: FragmentExerciseInfoBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_exercise_info, container, false)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel::class.java)
        exerciseViewModel.exercise.observe(this, Observer { exercise -> mBinding!!.exercise = exercise })
        exerciseViewModel.init(arguments!!.getInt(EXERCISE_ID_ARG))
    }



    companion object {

        private const val EXERCISE_ID_ARG = "exercise_id_arg"

        fun newInstance(exerciseId: Int): ExerciseInfoFragment {
            val args = Bundle()
            args.putInt(EXERCISE_ID_ARG, exerciseId)

            val fragment = ExerciseInfoFragment()
            fragment.arguments = args

            return fragment
        }
    }
}
