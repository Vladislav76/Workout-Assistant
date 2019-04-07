package com.vladislav.workoutassistant.ui.workouts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.ui.main.GeneralFragment;
import com.vladislav.workoutassistant.ui.main.interfaces.OnItemClickCallback;
import com.vladislav.workoutassistant.data.models.WorkoutExercise;
import com.vladislav.workoutassistant.data.models.WorkoutProgram;
import com.vladislav.workoutassistant.data.models.WorkoutSet;
import com.vladislav.workoutassistant.ui.exercises.ExercisesFragment;
import com.vladislav.workoutassistant.ui.workouts.adapters.SetAndExerciseAdapter;
import com.vladislav.workoutassistant.ui.main.components.DividerItemDecoration;
import com.vladislav.workoutassistant.ui.main.components.SimpleItemTouchHelperCallback;
import com.vladislav.workoutassistant.ui.workouts.viewmodels.WorkoutViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutDetailsFragment extends GeneralFragment implements SimpleItemTouchHelperCallback.OnStartDragListener {

    private static final String WORKOUT_ID_ARG = "workout_id_arg";
    private static final String TITLE_ARG = "title_arg";

    private RecyclerView mProgramRecyclerView;
    private WorkoutViewModel mWorkoutViewModel;
    private SetAndExerciseAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;

    private OnItemClickCallback mCallback = new OnItemClickCallback() {
        @Override
        public void onClick(int id, String name) {
            //TODO: some actions
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        getMFragmentListener().updateToolbarTitle(getArguments().getString(TITLE_ARG));
        setHasOptionsMenu(true);

        mProgramRecyclerView = view.findViewById(R.id.recycler_view);

        mWorkoutViewModel = ViewModelProviders.of(this).get(WorkoutViewModel.class);
        mAdapter = new SetAndExerciseAdapter(mCallback, this, mWorkoutViewModel.getMuscleGroups());
        mWorkoutViewModel.getSetsAndExercisesList().observe(this, new Observer<List<Object>>() {
            @Override
            public void onChanged(List<Object> list) {
                if (list != null) {
                    mAdapter.setList(list);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
        mWorkoutViewModel.init(getArguments().getInt(WORKOUT_ID_ARG));
        mProgramRecyclerView.setAdapter(mAdapter);
        mProgramRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.divider, 20));

        mItemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(mAdapter));
        mItemTouchHelper.attachToRecyclerView(mProgramRecyclerView);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.workouts_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_exercises_action:
                getMFragmentListener().addFragmentOnTop(ExercisesFragment.Companion.newInstance(true));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    public static WorkoutDetailsFragment newInstance(int id, String title) {
        Bundle args = new Bundle();
        args.putInt(WORKOUT_ID_ARG, id);
        args.putString(TITLE_ARG, title);

        WorkoutDetailsFragment fragment = new WorkoutDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
