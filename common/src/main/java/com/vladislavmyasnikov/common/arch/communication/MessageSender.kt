package com.vladislavmyasnikov.common.arch.communication

interface MessageSender {

    var defaultReceiver: MessageReceiver?

    fun sendMessage(message: Message, receiver: MessageReceiver? = null) {
        receiver?.receiveMessage(message, this)
                ?: defaultReceiver?.receiveMessage(message, this)
    }

    fun relayMessage(message: Message, sender: MessageSender, receiver: MessageReceiver? = null) {
        receiver?.receiveMessage(message, sender)
                ?: defaultReceiver?.receiveMessage(message, sender)
    }
}