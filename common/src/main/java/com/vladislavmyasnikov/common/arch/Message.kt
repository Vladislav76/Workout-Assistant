package com.vladislavmyasnikov.common.arch

enum class RequestMessageType {
    KEY_DATA_REQUEST, CONTENT_REQUEST, TRANSITION_REQUEST
}

sealed class Message {

    class RequestMessage(val type: RequestMessageType) : Message()
    class KeyDataResponseMessage(val id: Long) : Message()
    class ItemClickMessage(val id: Long) : Message()
    class NewItemMessage : Message()
    class Noise : Message()
}