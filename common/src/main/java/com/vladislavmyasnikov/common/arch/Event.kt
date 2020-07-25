package com.vladislavmyasnikov.common.arch

sealed class Event {

    class SelectionModeChangeEvent(val isSelected: Boolean) : Event()
    class SelectedItemAmountChangeEvent(val amount: Int) : Event()
    class SelectedItemIdChangeEvent(val id: Long) : Event()
}