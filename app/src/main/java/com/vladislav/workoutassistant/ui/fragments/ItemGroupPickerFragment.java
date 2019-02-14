package com.vladislav.workoutassistant.ui.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladislav.workoutassistant.R;
import com.vladislav.workoutassistant.data.model.SelectableItem;
import com.vladislav.workoutassistant.databinding.ListItemSelectableObjectBinding;
import com.vladislav.workoutassistant.viewmodels.SelectableItemViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ItemGroupPickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mSelectedItemIdList = getArguments().getIntegerArrayList(ARG_SELECTED_ITEM_ID_LIST);
        String[] itemNameArray = getArguments().getStringArray(ARG_ITEM_NAME_ARRAY);
        mItems = getItemList(mSelectedItemIdList, itemNameArray);

        RecyclerView view = new RecyclerView(getActivity());
        view.setLayoutManager(new LinearLayoutManager(getActivity()));
        view.setAdapter(new ItemGroupAdapter(mItems));

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK, getSelectedItemIdArrayList(mItems));
                    }
                })
                .create();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putIntegerArrayList(ARG_SELECTED_ITEM_ID_LIST, getSelectedItemIdArrayList(mItems));
    }

    private void sendResult(int resultCode, ArrayList<Integer> selectedItemIdList) {
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_SELECTED_ITEM_ID_LIST, selectedItemIdList);
            targetFragment.onActivityResult(getTargetRequestCode(), resultCode, intent);
        }
    }

    private ArrayList<SelectableItem> getItemList(List<Integer> selectedItemIdList, String[] itemNameArray) {
        if (selectedItemIdList == null || itemNameArray == null) {
            return null;
        }

        ArrayList<SelectableItem> items = new ArrayList<>(itemNameArray.length);
        for (int i = 0; i < itemNameArray.length; i++) {
            items.add(new SelectableItem(itemNameArray[i], false));
        }

        for (Integer id : selectedItemIdList) {
            if (id >= 0 && id < itemNameArray.length) {
                items.get(id).setSelected(true);
            }
            else {
                return null;
            }
        }
        return items;
    }

    private ArrayList<Integer> getSelectedItemIdArrayList(ArrayList<SelectableItem> items) {
        int id = 0;
        int position = 0;
        int oldSelectedItemNumber = mSelectedItemIdList.size();

        for (SelectableItem item : items) {
            if (item.isSelected()) {
                if (position < oldSelectedItemNumber) {
                    mSelectedItemIdList.set(position, id);
                }
                else {
                    mSelectedItemIdList.add(id);
                }
                position++;
            }
            id++;
        }

        for (int i = oldSelectedItemNumber - 1; i >= position ; i--) {
            mSelectedItemIdList.remove(i);
        }

        return mSelectedItemIdList;
    }

    public static ItemGroupPickerFragment newInstance(ArrayList<Integer> selectedItemIdList, String[] itemNameArray) {
        Bundle args = new Bundle();
        args.putIntegerArrayList(ARG_SELECTED_ITEM_ID_LIST, selectedItemIdList);
        args.putStringArray(ARG_ITEM_NAME_ARRAY, itemNameArray);

        ItemGroupPickerFragment fragment = new ItemGroupPickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private class ItemGroupHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private ListItemSelectableObjectBinding mBinding;

        public ItemGroupHolder(ListItemSelectableObjectBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(new SelectableItemViewModel());
            itemView.setOnClickListener(this);
            mBinding.itemCheckField.setOnClickListener(this);
        }

        public void bind(SelectableItem item) {
            mBinding.getViewModel().setSelectableItem(item);
            mBinding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            mBinding.getViewModel().setSelected(!mBinding.getViewModel().isSelected());
        }
    }

    private class ItemGroupAdapter extends RecyclerView.Adapter<ItemGroupHolder> {
        private List<SelectableItem> mItems;

        public ItemGroupAdapter(List<SelectableItem> items) {
            mItems = items;
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        @Override
        public ItemGroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemSelectableObjectBinding binding =
                    DataBindingUtil.inflate(inflater, R.layout.list_item_selectable_object, parent, false);
            return new ItemGroupHolder(binding);
        }

        @Override
        public void onBindViewHolder(ItemGroupHolder holder, int position) {
            holder.bind(mItems.get(position));
        }
    }

    private ArrayList<SelectableItem> mItems;
    private ArrayList<Integer> mSelectedItemIdList;

    private static final String ARG_SELECTED_ITEM_ID_LIST = "arg_selected_item_id_list";
    private static final String ARG_ITEM_NAME_ARRAY = "arg_item_name_array";
    public static final String EXTRA_SELECTED_ITEM_ID_LIST = "extra_selected_item_id_list";
}
