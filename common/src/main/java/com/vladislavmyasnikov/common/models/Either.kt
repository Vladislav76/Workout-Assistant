package com.vladislavmyasnikov.common.models

sealed class Either<L,R> {

    class Left<L,R>(val value: L) : Either<L,R>()
    class Right<L,R>(val value: R) : Either<L,R>()
}