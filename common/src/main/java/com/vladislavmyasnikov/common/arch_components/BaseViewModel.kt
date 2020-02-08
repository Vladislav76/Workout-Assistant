package com.vladislavmyasnikov.common.arch_components

import android.util.Log
import androidx.lifecycle.ViewModel
import com.vladislavmyasnikov.common.utils.Logger
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel<T, E> : ViewModel() {

    abstract val label: String

    private val _items = BehaviorSubject.create<T>()
    private val _events = PublishSubject.create<Event>()
    private val _errors = PublishSubject.create<E>()
    protected val disposables = CompositeDisposable()

    val items: Observable<T> = _items
    val errors: Observable<E> = _errors
    val events: Observable<Event> = _events

    protected fun pushItem(item: T) {
        _items.onNext(item)
        Logger.debug(label, "New item: $item")
    }

    protected fun pushError(error: E) {
        _errors.onNext(error)
        Logger.debug(label, "New error: $error")
    }

    protected fun pushEvent(event: Event) {
        _events.onNext(event)
        Logger.debug(label, "New event: $event")
    }

    protected fun debugMessage(message: String) {
        Log.d(label, message)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}