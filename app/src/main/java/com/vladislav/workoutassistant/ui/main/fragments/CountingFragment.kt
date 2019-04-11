package com.vladislav.workoutassistant.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton

import com.vladislav.workoutassistant.R

import java.util.Locale
import androidx.fragment.app.Fragment

class CountingFragment : Fragment(), View.OnClickListener {

    private var mCountField: EditText? = null

    val currentValue: Int
        get() {
            val string = mCountField!!.text.toString()
            return if (string == "") arguments!!.getInt(COUNT_ARG) else Integer.parseInt(string)
        }

    enum class MeasureUnit {
        KM, KG
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_count_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mCountField = view.findViewById(R.id.count_field)

        val decrementButton = view.findViewById<ImageButton>(R.id.decrement_button)
        decrementButton.setOnClickListener(this)

        val incrementButton = view.findViewById<ImageButton>(R.id.increment_button)
        incrementButton.setOnClickListener(this)

        if (savedInstanceState == null) {
            recountAndUpdate(currentValue, 0)
            Log.d("COUNTING_FRAGMENT", "BUNDLE NULL")
        } else {
            Log.d("COUNTING_FRAGMENT", "BUNDLE NOT NULL")
            val currentCount = savedInstanceState.getInt(STORED_DATA)
            recountAndUpdate(currentCount, 0)
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt(STORED_DATA, currentValue)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.decrement_button -> recountAndUpdate(currentValue, -1)
            R.id.increment_button -> recountAndUpdate(currentValue, 1)
        }
    }

    /* P R I V A T E */
    private fun recountAndUpdate(value: Int, offset: Int) {
        val newValue = value + offset
        mCountField!!.setText(String.format(Locale.getDefault(), "%d",
                if (newValue > MINIMUM_COUNT) if (newValue < MAXIMUM_COUNT) newValue else MAXIMUM_COUNT else MINIMUM_COUNT))
    }

    companion object {

        private val MINIMUM_COUNT = 0
        private val MAXIMUM_COUNT = 99999

        private val COUNT_ARG = "count_arg"
        private val MEASURE_UNITS_ARG = "measure_units_arg"

        private val STORED_DATA = "stored_data"

        /* F A StringConverter T O R Y */
        fun newInstance(count: Int, unit: MeasureUnit): CountingFragment {
            val args = Bundle()
            args.putInt(COUNT_ARG, count)
            args.putSerializable(MEASURE_UNITS_ARG, unit)

            val fragment = CountingFragment()
            fragment.arguments = args

            return fragment
        }
    }
}
