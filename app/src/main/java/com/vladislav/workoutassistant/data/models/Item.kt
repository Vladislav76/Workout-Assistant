package com.vladislav.workoutassistant.data.models

data class Item(override val id: Int, override var name: String) : Identifiable, Nameable