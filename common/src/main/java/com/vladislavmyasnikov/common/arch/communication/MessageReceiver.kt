package com.vladislavmyasnikov.common.arch.communication

interface MessageReceiver {

    fun receiveMessage(message: Message, sender: MessageSender)
}