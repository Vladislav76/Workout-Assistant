package com.vladislavmyasnikov.common.arch.communication

interface Message

sealed class Messages : Message {

    object KeyDataRequestMessage : Messages()
    object TransitionRequestMessage : Messages()
    class ItemClickMessage(val id: Long) : Messages()
    class KeyDataResponseMessage(val id: Long) : Messages()
}

