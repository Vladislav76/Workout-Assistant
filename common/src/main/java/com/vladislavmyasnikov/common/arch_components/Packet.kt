package com.vladislavmyasnikov.common.arch_components

sealed class Packet {

    class ItemClickMessage(val id: Long, var isProcessed: Boolean = false) : Packet()
    class EmptyMessage : Packet()
}