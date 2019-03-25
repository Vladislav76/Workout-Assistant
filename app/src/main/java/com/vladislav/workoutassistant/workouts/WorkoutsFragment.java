package com.vladislav.workoutassistant.workouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.core.GeneralFragment;
import com.vladislav.workoutassistant.core.callbacks.ItemClickCallback;
import com.vladislav.workoutassistant.core.components.CustomItemDecoration;
import com.vladislav.workoutassistant.data.model.WorkoutGroup;
import com.vladislav.workoutassistant.workouts.adapters.CategoryAdapter;
import com.vladislav.workoutassistant.workouts.adapters.WorkoutGroupAdapter;
import com.vladislav.workoutassistant.workouts.viewmodels.WorkoutGroupListViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutsFragment extends GeneralFragment {

    private static final String CURRENT_CATEGORY_ID = "current_category_id";

    private RecyclerView mWorkoutsCardsRecyclerView;
    private WorkoutGroupAdapter mWorkoutGroupAdapter;
    private WorkoutGroupListViewModel mWorkoutGroupListViewModel;
    private int mCurrentCategoryId;

    private ItemClickCallback mCategoryClickCallback = new ItemClickCallback() {
        @Override
        public void onClick(int id, String name) {
            if (id != mCurrentCategoryId) {
                mCurrentCategoryId = id;
                mWorkoutGroupListViewModel.init(id);
            }
        }
    };
    private ItemClickCallback mWorkoutGroupClickCallback = new ItemClickCallback() {
        @Override
        public void onClick(int id, String name) {
            Toast.makeText(getActivity(), name + ", id:" + id, Toast.LENGTH_SHORT).show();
        }
    };
    private ItemClickCallback mWorkoutClickCallback = new ItemClickCallback() {
        @Override
        public void onClick(int id, String name) {
            mFragmentListener.addFragmentOnTop(WorkoutDetailsFragment.newInstance(id, name));
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_horizontal_and_vertical_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        updateToolbar(R.string.workouts_tab);

        if (savedInstanceState != null) {
            mCurrentCategoryId = savedInstanceState.getInt(CURRENT_CATEGORY_ID);
        }

        mWorkoutGroupListViewModel = ViewModelProviders.of(this).get(WorkoutGroupListViewModel.class);

        final RecyclerView categoriesRecyclerView = view.findViewById(R.id.horizontal_recycler_view);
        CategoryAdapter categoryAdapter = new CategoryAdapter(mWorkoutGroupListViewModel.getCategories(), mCategoryClickCallback);
        categoriesRecyclerView.setAdapter(categoryAdapter);
        categoriesRecyclerView.addItemDecoration(new CustomItemDecoration(10));

        mWorkoutsCardsRecyclerView = view.findViewById(R.id.vertical_recycler_view);
        mWorkoutGroupAdapter = new WorkoutGroupAdapter(mWorkoutGroupClickCallback, mWorkoutClickCallback);
        mWorkoutsCardsRecyclerView.setAdapter(mWorkoutGroupAdapter);
        mWorkoutGroupListViewModel.getWorkoutGroups().observe(this, new Observer<List<WorkoutGroup>>() {
            @Override
            public void onChanged(List<WorkoutGroup> workoutGroups) {
                if (workoutGroups != null) {
                    mWorkoutGroupAdapter.setList(workoutGroups);
                    mWorkoutGroupAdapter.notifyDataSetChanged();
                    mWorkoutsCardsRecyclerView.scrollToPosition(0);
                    ((LinearLayoutManager)categoriesRecyclerView.getLayoutManager()).scrollToPositionWithOffset(mCurrentCategoryId, 0);
                }
            }
        });
        mWorkoutGroupListViewModel.init(mCurrentCategoryId);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putInt(CURRENT_CATEGORY_ID, mCurrentCategoryId);
    }

    public static WorkoutsFragment newInstance() {
        return new WorkoutsFragment();
    }
}