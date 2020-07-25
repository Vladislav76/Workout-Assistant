package com.vladislavmyasnikov.common.arch

import com.vladislavmyasnikov.common.utils.Logger
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class SharedBus {

    companion object {
        private const val TAG = "shared_bus"
    }

    private var _packets = BehaviorSubject.create<Message>()

    val packets: Observable<Message> = _packets

    fun sendPacket(message: Message) {
        _packets.onNext(message)
        Logger.debug(TAG, "Packet is sent $message")
    }

    fun sendNoise() {
        _packets.onNext(Message.Noise())
    }
}