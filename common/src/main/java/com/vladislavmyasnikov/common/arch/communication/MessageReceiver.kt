package com.vladislavmyasnikov.common.arch.communication

interface MessageReceiver {

    fun onReceiveMessage(message: Message, sender: MessageSender)
}