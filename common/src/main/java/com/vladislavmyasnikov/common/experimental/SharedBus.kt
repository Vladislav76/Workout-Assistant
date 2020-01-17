package com.vladislavmyasnikov.common.experimental

import com.vladislavmyasnikov.common.utils.Logger
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class SharedBus {

    companion object {
        private const val TAG = "shared_bus"
    }

    private val _packets = PublishSubject.create<Packet>()

    val packets: Observable<Packet> = _packets

    fun sendPacket(packet: Packet) {
        _packets.onNext(packet)
        Logger.debug(TAG, "Packet is sent $packet")
    }
}