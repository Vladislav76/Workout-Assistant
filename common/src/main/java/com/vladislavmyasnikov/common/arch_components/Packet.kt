package com.vladislavmyasnikov.common.arch_components

sealed class Packet {

    class ItemClickMessage(val id: Long) : Packet()
    class ItemFetchRequest(val id: Long) : Packet()
    class NewItemMessage : Packet()
    class Noise : Packet()
}