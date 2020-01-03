package com.vladislavmyasnikov.common.view

import android.app.Activity
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.DialogFragment
import com.vladislavmyasnikov.common.models.TimePoint

class TimePickerFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val initialTime = arguments!!.getParcelable<TimePoint>(TIME_ARG)!!

        return TimePickerDialog(
                activity,
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    sendResult(Activity.RESULT_OK, TimePoint(hourOfDay.toLong(), minute.toLong()))
                },
                initialTime.hours.toInt(),
                initialTime.minutes.toInt(),
                DateFormat.is24HourFormat(activity)
        )
    }

    private fun sendResult(resultCode: Int, time: TimePoint) {
        targetFragment?.let {
            val intent = Intent().apply {
                putExtra(TIME_EXTRA, time)
            }
            it.onActivityResult(targetRequestCode, resultCode, intent)
        }
    }

    companion object {
        private const val TIME_EXTRA = "time_extra"
        private const val TIME_ARG = "time_arg"

        fun newInstance(time: TimePoint): TimePickerFragment {
            return TimePickerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(TIME_ARG, time)
                }
            }
        }

        fun extractTime(data: Intent): TimePoint {
            return data.getParcelableExtra(TIME_EXTRA)!!
        }
    }
}
