package com.vladislavmyasnikov.common.experimental

sealed class Result<A, B> {

    class Success<A>(val data: A) : Result<A, Nothing>()

    class Error<B>(val data: B) : Result<Nothing, B>()
}