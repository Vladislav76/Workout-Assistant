package com.vladislavmyasnikov.core_components.view

import android.app.Activity
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.sql.Time
import java.util.*

class TimePickerFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val initialTime = arguments!!.getSerializable(TIME_ARG) as Date
        val calendar = Calendar.getInstance().apply { time = initialTime }

        return TimePickerDialog(
                activity,
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    val newDate = GregorianCalendar(0, 0, 0, hourOfDay, minute)
                    sendResult(Activity.RESULT_OK, Time(newDate.time.time))
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(activity)
        )
    }

    private fun sendResult(resultCode: Int, date: Date) {
        targetFragment?.let {
            val intent = Intent().apply {
                putExtra(TIME_EXTRA, date)
            }
            it.onActivityResult(targetRequestCode, resultCode, intent)
        }
    }

    companion object {
        private const val TIME_EXTRA = "time_extra"
        private const val TIME_ARG = "time_arg"

        fun newInstance(time: Date): TimePickerFragment {
            return TimePickerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(TIME_ARG, time)
                }
            }
        }

        fun extractTime(data: Intent): Time {
            return data.getSerializableExtra(TIME_EXTRA) as Time
        }
    }
}
