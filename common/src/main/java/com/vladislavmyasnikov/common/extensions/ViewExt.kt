package com.vladislavmyasnikov.common.extensions

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import kotlinx.android.synthetic.main.plus_minus_input_panel.view.*

inline val View.exported_increase_button get(): ImageButton = increase_button
inline val View.exported_decrease_button get(): ImageButton = decrease_button
inline val View.exported_data_button get(): Button = data_button