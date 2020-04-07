package com.vladislavmyasnikov.common.arch_components

import androidx.lifecycle.ViewModel
import com.vladislavmyasnikov.common.utils.Logger
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel<T, E> : ViewModel() {

    private val itemSubject = BehaviorSubject.create<T>()
    private val eventSubject = PublishSubject.create<Event>()
    private val errorSubject = PublishSubject.create<E>()
    protected val disposables = CompositeDisposable()

    val items: Observable<T> = itemSubject
    val errors: Observable<E> = errorSubject
    val events: Observable<Event> = eventSubject

    protected fun pushItem(item: T) {
        itemSubject.onNext(item)
        Logger.debug(this::class, "New item: $item")
    }

    protected fun pushError(error: E) {
        errorSubject.onNext(error)
        Logger.debug(this::class, "New error: $error")
    }

    protected fun pushEvent(event: Event) {
        eventSubject.onNext(event)
        Logger.debug(this::class, "New event: $event")
    }

    protected fun debugMessage(message: String) {
        Logger.debug(this::class, message)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}