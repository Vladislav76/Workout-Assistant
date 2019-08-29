package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.vladislavmyasnikov.feature_exercise_library_impl.R

class ExerciseFilterFragment : DialogFragment() {

    private lateinit var muscleGroups: Array<String>
    private lateinit var tagContainer: ChipGroup

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view: View = activity!!.layoutInflater.inflate(R.layout.chips_container, null)
        tagContainer = view.findViewById(R.id.chip_group)
        val selectedItemsIDs = arguments!!.getIntArray(SELECTED_ITEMS_IDS_ARG)!!
        muscleGroups = activity!!.resources.getStringArray(R.array.muscle_groups)

        for ((index, muscleGroup) in muscleGroups.withIndex()) {
            val tag = Chip(activity!!).apply {
                id = index
                text = muscleGroup
                isCheckable = true
                chipBackgroundColor = ContextCompat.getColorStateList(activity!!, R.color.tag_state_list)
                checkedIcon = null
                if (index in selectedItemsIDs) {
                    isChecked = true
                }
            }
            tagContainer.addView(tag)
        }

        return AlertDialog.Builder(activity)
                .setView(view)
                .setPositiveButton(android.R.string.ok) { _: DialogInterface, _: Int ->
                    sendResult(Activity.RESULT_OK)
                }
                .setNeutralButton(R.string.discard_action) { _: DialogInterface, _: Int ->
                    sendResult(Activity.RESULT_CANCELED)
                }
                .setNegativeButton(android.R.string.cancel, null)
                .create()
    }

    private fun getSelectedItemsIDs(): IntArray {
        val result = mutableListOf<Int>()
        for (i in 0 until tagContainer.childCount) {
            if ((tagContainer.getChildAt(i) as Chip).isChecked) {
                result.add(i)
            }
        }
        return result.toIntArray()
    }

    private fun sendResult(resultCode: Int) {
        targetFragment?.let {
            val intent = when (resultCode) {
                Activity.RESULT_OK -> {
                    Intent().apply {
                        putExtra(SELECTED_ITEMS_IDS_EXTRA, getSelectedItemsIDs())
                    }
                }
                Activity.RESULT_CANCELED -> null
                else -> return
            }
            it.onActivityResult(targetRequestCode, resultCode, intent)
        }
    }

    companion object {
        private const val SELECTED_ITEMS_IDS_ARG = "selected_items_ids_arg"
        private const val SELECTED_ITEMS_IDS_EXTRA = "selected_items_ids_extra"

        fun newInstance(selectedItemsIDs: IntArray): ExerciseFilterFragment {
            return ExerciseFilterFragment().apply {
                arguments = Bundle().apply {
                    putIntArray(SELECTED_ITEMS_IDS_ARG, selectedItemsIDs)
                }
            }
        }

        fun extractData(intent: Intent): IntArray {
            return intent.getIntArrayExtra(SELECTED_ITEMS_IDS_EXTRA)
        }
    }
}