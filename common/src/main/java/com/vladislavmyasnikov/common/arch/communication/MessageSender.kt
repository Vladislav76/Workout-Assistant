package com.vladislavmyasnikov.common.arch.communication

interface MessageSender {

    var defaultReceiver: MessageReceiver?

    fun sendMessage(message: Message, receiver: MessageReceiver? = null) {
        receiver?.onReceiveMessage(message, this)
                ?: defaultReceiver?.onReceiveMessage(message, this)
    }

    fun relayMessage(message: Message, sender: MessageSender, receiver: MessageReceiver? = null) {
        receiver?.onReceiveMessage(message, sender)
                ?: defaultReceiver?.onReceiveMessage(message, sender)
    }
}