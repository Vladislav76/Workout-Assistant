package com.vladislav.workoutassistant.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.db.entity.ProgramCategoryEntity;
import com.vladislav.workoutassistant.ui.adapters.ProgramCategoryAdapter;
import com.vladislav.workoutassistant.ui.callbacks.ItemClickCallback;
import com.vladislav.workoutassistant.ui.callbacks.OnFragmentListener;
import com.vladislav.workoutassistant.viewmodels.ProgramCategoriesViewModel;

import java.util.List;

public class ProgramCategoriesFragment extends GeneralFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        updateToolbar(R.string.program_categories_toolbar_title);

        final RecyclerView recyclerView = new RecyclerView(getActivity());
        if (mAdapter == null) {
            mAdapter = new ProgramCategoryAdapter(mCallback);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);

        ProgramCategoriesViewModel programCategoriesViewModel = ViewModelProviders.of(this).get(ProgramCategoriesViewModel.class);
        programCategoriesViewModel.getCategories().observe(this, new Observer<List<ProgramCategoryEntity>>() {
            @Override
            public void onChanged(List<ProgramCategoryEntity> categories) {
                if (categories != null) {
                    mAdapter.setList(categories);
                    mAdapter.notifyItemChanged(0, categories.size());
                }
            }
        });

        return recyclerView;
    }

    public static ProgramCategoriesFragment newInstance() {
        return new ProgramCategoriesFragment();
    }

    private ProgramCategoryAdapter mAdapter;
    private ItemClickCallback mCallback = new ItemClickCallback() {
        @Override
        public void onClick(int id, String title) {
            ProgramsFragment fragment = ProgramsFragment.newInstance(id, title);
            mFragmentListener.addFragmentOnTop(fragment);
        }
    };
}