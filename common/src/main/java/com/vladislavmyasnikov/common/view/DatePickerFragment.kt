package com.vladislavmyasnikov.common.view

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = arguments!!.getSerializable(DATE_ARG) as Date
        val calendar = Calendar.getInstance().apply { time = date }

        return DatePickerDialog(
                activity!!,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    val newDate = GregorianCalendar(year, month, dayOfMonth).time
                    sendResult(Activity.RESULT_OK, newDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    private fun sendResult(resultCode: Int, date: Date) {
        targetFragment?.let {
            val intent = Intent().apply {
                putExtra(DATE_EXTRA, date)
            }
            it.onActivityResult(targetRequestCode, resultCode, intent)
        }
    }

    companion object {
        private const val DATE_EXTRA = "date_extra"
        private const val DATE_ARG = "date_arg"

        fun newInstance(date: Date): DatePickerFragment {
            return DatePickerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(DATE_ARG, date)
                }
            }
        }

        fun extractDate(data: Intent): Date {
            return data.getSerializableExtra(DATE_EXTRA) as Date
        }
    }
}
