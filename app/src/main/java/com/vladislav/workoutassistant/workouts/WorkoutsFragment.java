package com.vladislav.workoutassistant.workouts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.db.entity.Category;
import com.vladislav.workoutassistant.core.GeneralFragment;
import com.vladislav.workoutassistant.workouts.adapters.CategoryAdapter;
import com.vladislav.workoutassistant.workouts.adapters.WorkoutsCardAdapter;
import com.vladislav.workoutassistant.core.callbacks.ItemClickCallback;
import com.vladislav.workoutassistant.workouts.viewmodels.VerticalModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutsFragment extends GeneralFragment {

    private RecyclerView mCategoriesRecyclerView;
    private RecyclerView mWorkoutsCardsRecyclerView;
    private CategoryAdapter mCategoryAdapter;
    private WorkoutsCardAdapter mWorkoutsCardAdapter;

    private ItemClickCallback mCategoryClickCallback = new ItemClickCallback() {
        @Override
        public void onClick(int id, String name) {
            Random random = new Random();
            Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
            mWorkoutsCardAdapter.setList(generateWorkoutsCards(random.nextInt(7), name));
//            mWorkoutsCardsRecyclerView.setAdapter(mWorkoutsCardAdapter);

        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workouts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        updateToolbar(R.string.workouts_tab);

        mCategoriesRecyclerView = view.findViewById(R.id.horizontal_recycler_view);
        mWorkoutsCardsRecyclerView = view.findViewById(R.id.vertical_recycler_view);

        mCategoryAdapter = new CategoryAdapter(generateCategories(10), mCategoryClickCallback);
        mCategoriesRecyclerView.setAdapter(mCategoryAdapter);

        mWorkoutsCardAdapter = new WorkoutsCardAdapter(generateWorkoutsCards(10, "Start"));
        mWorkoutsCardsRecyclerView.setAdapter(mWorkoutsCardAdapter);

        //TODO: add custom Item Decorator
    }

    public static WorkoutsFragment newInstance() {
        return new WorkoutsFragment();
    }

    /* PROTOTYPE DATA */
    private List<Category> generateCategories(int size) {
        List<Category> categories = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            categories.add(new Category("Category " + i));
        }
        return categories;
    }

    private List<VerticalModel> generateWorkoutsCards(int size, String categoryName) {
        List<VerticalModel> cards = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            cards.add(new VerticalModel(categoryName + ". Level " + i));
        }
        return cards;
    }
}

//    private ItemClickCallback mCallback = new ItemClickCallback() {
//        @Override
//        public void onClick(int id, String title) {
//            ProgramsFragment fragment = ProgramsFragment.newInstance(id, title);
//            mFragmentListener.addFragmentOnTop(fragment);
//        }
//    };

//
//        ProgramCategoriesViewModel programCategoriesViewModel = ViewModelProviders.of(this).get(ProgramCategoriesViewModel.class);
//        programCategoriesViewModel.getCategories().observe(this, new Observer<List<ProgramCategoryEntity>>() {
//            @Override
//            public void onChanged(List<ProgramCategoryEntity> categories) {
//                if (categories != null) {
//                    mAdapter.setList(categories);
//                    mAdapter.notifyItemChanged(0, categories.size());
//                }
//            }
//        });