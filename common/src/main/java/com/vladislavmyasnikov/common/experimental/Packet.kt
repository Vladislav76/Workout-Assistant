package com.vladislavmyasnikov.common.experimental

sealed class Packet {

    class ItemClickMessage(val id: Long, var isProcessed: Boolean = false) : Packet()
}