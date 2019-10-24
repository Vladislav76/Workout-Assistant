package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters.NumberCellAdapter

class NumberSelectionFragment : DialogFragment() {

    private var selectedNumber: Long = 0

    private val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            selectedNumber = id
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val maxNumber = arguments!!.getInt(MAX_NUMBER_ARG)
        val view: View = activity!!.layoutInflater.inflate(R.layout.grid_recycler_view, null)

        view.findViewById<RecyclerView>(R.id.grid_recycler_view).apply {
            adapter = NumberCellAdapter(context, (1..maxNumber).toList()).apply {
                callback = itemClickCallback
            }
            (layoutManager as? GridLayoutManager)?.spanCount = 5
        }

        savedInstanceState?.let {
            selectedNumber = it.getLong(SELECTED_NUMBER_EXTRA)
        }

        return AlertDialog.Builder(activity)
                .setView(view)
                .setPositiveButton(android.R.string.ok) { _: DialogInterface, _: Int ->
                    sendResult(selectedNumber.toInt())
                }
                .setNegativeButton(android.R.string.cancel, null)
                .create()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putLong(SELECTED_NUMBER_EXTRA, selectedNumber)
    }

    private fun sendResult(num: Int) {
        targetFragment?.let {
            val intent = Intent().apply {
                putExtra(MAX_NUMBER_EXTRA, num)
            }
            it.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        }
    }

    companion object {
        private const val MAX_NUMBER_EXTRA = "max_number_extra"
        private const val SELECTED_NUMBER_EXTRA = "selected_number_extra"
        private const val MAX_NUMBER_ARG = "max_number_arg"

        fun newInstance(max: Int): NumberSelectionFragment {
            return NumberSelectionFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(MAX_NUMBER_ARG, max)
                }
            }
        }

        fun extractSelectedNumber(data: Intent): Int {
            return data.getIntExtra(MAX_NUMBER_EXTRA, 0)
        }
    }
}