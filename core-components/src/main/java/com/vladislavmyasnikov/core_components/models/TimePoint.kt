package com.vladislavmyasnikov.core_components.models

import android.os.Parcel
import android.os.Parcelable

class TimePoint : Parcelable {

    val hours: Long
    val minutes: Long
    val seconds: Long
    private val ms: Long

    constructor(parcel: Parcel) {
        hours = parcel.readLong()
        minutes = parcel.readLong()
        seconds = parcel.readLong()
        ms = parcel.readLong()
    }

    constructor(_ms: Long) {
        hours = _ms / MILLISECONDS_IN_HOUR
        var tail = _ms % MILLISECONDS_IN_HOUR

        minutes = tail / MILLISECONDS_IN_MINUTE
        tail %= MILLISECONDS_IN_MINUTE

        seconds = tail / MILLISECONDS_IN_SECOND
        ms = _ms - tail % MILLISECONDS_IN_SECOND
    }

    constructor(_hours: Long, _minutes: Long) {
        hours = _hours
        minutes = _minutes
        seconds = 0
        ms = convertToMilliseconds(hours, minutes, seconds)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(hours)
        dest.writeLong(minutes)
        dest.writeLong(seconds)
        dest.writeLong(ms)
    }

    override fun describeContents(): Int = 0

    fun toMilliseconds(): Long = ms

    fun before(other: TimePoint): Boolean = ms < other.ms

    fun after(other: TimePoint): Boolean = ms > other.ms

    companion object CREATOR : Parcelable.Creator<TimePoint> {
        private const val MILLISECONDS_IN_HOUR = 3_600_000
        private const val MILLISECONDS_IN_MINUTE = 60_000
        private const val MILLISECONDS_IN_SECOND = 1_000

        override fun createFromParcel(parcel: Parcel): TimePoint {
            return TimePoint(parcel)
        }

        override fun newArray(size: Int): Array<TimePoint?> {
            return arrayOfNulls(size)
        }

        private fun convertToMilliseconds(hours: Long, minutes: Long, seconds: Long): Long {
            return hours * MILLISECONDS_IN_HOUR + minutes * MILLISECONDS_IN_MINUTE + seconds * MILLISECONDS_IN_SECOND
        }
    }
}