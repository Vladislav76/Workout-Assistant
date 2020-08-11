package com.vladislavmyasnikov.common.arch.viewmodel

import com.vladislavmyasnikov.common.models.Either
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class SimpleVM<T> : BaseVM<T, Throwable>() {

    private var isRequested = false
    private var lastID = 0L

    fun request(id: Long = -1) {
        if (!isRequested) {
            if (id != lastID) {
                isRequested = true
                when (val response = processRequest(id)) {
                    is Either.Left -> {
                        val isSuccessful = response.value
                        if (isSuccessful) lastID = id
                        isRequested = false
                    }
                    is Either.Right -> {
                        val willSuccessful = response.value
                        willSuccessful.subscribeOn(Schedulers.io())
                                .doFinally { isRequested = false }
                                .subscribe { lastID = id }
                                .also { disposable -> disposables.add(disposable) }
                    }
                }
            }
        }
    }

    protected fun initAsynchronousRequest(channel: Single<T>): Completable {
        return channel.doOnSuccess { item -> pushItem(item) }
                .doOnError { error -> pushError(error) }
                .ignoreElement()
    }

    protected fun initAsynchronousRequest(channel: Observable<T>): Completable {
        channel.subscribeOn(Schedulers.io())
                .subscribe(
                        { item -> pushItem(item); onSuccessfulResponse(item) },
                        { error -> pushError(error) }
                )
                .also { disposable -> disposables.add(disposable) }
        return channel.firstOrError().ignoreElement()
    }

    /**
     * If (id < 0) then a simple request without arguments should be sent
     * @return response packed inside Either: Boolean (synchronous processing) or Completable (asynchronous processing)
     */
    protected abstract fun processRequest(id: Long): Either<Boolean, Completable>

    protected open fun onSuccessfulResponse(item: T) {}
}