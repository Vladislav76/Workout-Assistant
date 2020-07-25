package com.vladislavmyasnikov.common.interfaces

import com.vladislavmyasnikov.common.arch.Message

interface MessageReceiver {

    fun receiveMessage(message: Message, sender: MessageSender)
}