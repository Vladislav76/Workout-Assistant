package com.vladislavmyasnikov.common.extensions

import android.view.View
import kotlinx.android.synthetic.main.plus_minus_input_panel.view.*

inline val View.exported_increase_button get() = increase_button
inline val View.exported_decrease_button get() = decrease_button
inline val View.exported_data_button get() = data_button