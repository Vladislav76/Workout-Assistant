package com.vladislav.workoutassistant.ui;

import android.content.Context;
import android.os.Bundle;

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

public class ProgramCategoriesFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentListener) {
            mFragmentListener = (OnFragmentListener) context;
        }
        else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView view = new RecyclerView(getActivity());

        mAdapter = new ProgramCategoryAdapter(mCallback);
        view.setLayoutManager(new LinearLayoutManager(getActivity()));
        view.setAdapter(mAdapter);
        mProgramCategoriesViewModel = ViewModelProviders.of(this).get(ProgramCategoriesViewModel.class);
        mProgramCategoriesViewModel.getCategories().observe(this, new Observer<List<ProgramCategoryEntity>>() {
            @Override
            public void onChanged(List<ProgramCategoryEntity> categories) {
                if (categories != null) {
                    mAdapter.setList(categories);
                    mAdapter.notifyItemChanged(0, categories.size());
                }
            }
        });

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.program_categories_toolbar_title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mFragmentListener = null;
    }

    public static ProgramCategoriesFragment newInstance() {
        return new ProgramCategoriesFragment();
    }

    private OnFragmentListener mFragmentListener;
    private ProgramCategoriesViewModel mProgramCategoriesViewModel;
    private ProgramCategoryAdapter mAdapter;
    private ItemClickCallback mCallback = new ItemClickCallback() {
        @Override
        public void onClick(int id) {
            ProgramsFragment fragment = ProgramsFragment.newInstance(id);
            mFragmentListener.addFragmentOnTop(fragment);
        }
    };
}