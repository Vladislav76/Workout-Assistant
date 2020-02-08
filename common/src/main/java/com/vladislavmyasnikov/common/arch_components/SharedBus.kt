package com.vladislavmyasnikov.common.arch_components

import com.vladislavmyasnikov.common.utils.Logger
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class SharedBus {

    companion object {
        private const val TAG = "shared_bus"
    }

    private var _packets = BehaviorSubject.create<Packet>()

    val packets: Observable<Packet> = _packets

    fun sendPacket(packet: Packet) {
        _packets.onNext(packet)
        Logger.debug(TAG, "Packet is sent $packet")
    }

    fun sendNoise() {
        _packets.onNext(Packet.Noise())
    }
}