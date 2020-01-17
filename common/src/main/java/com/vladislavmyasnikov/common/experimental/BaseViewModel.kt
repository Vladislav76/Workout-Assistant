package com.vladislavmyasnikov.common.experimental

import androidx.lifecycle.ViewModel
import com.vladislavmyasnikov.common.utils.Logger
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel<T, E> : ViewModel() {

    enum class RequestState {
        Loaded, Saved, Deleted
    }

    companion object {
        const val TAG = "base_view_model"
    }

    private val _verdicts = BehaviorSubject.create<RequestState>()
    private val _items = BehaviorSubject.create<T>()
    private val _events = PublishSubject.create<Packet>()
    private val _errors = PublishSubject.create<E>()
    private var isLoading = false
    protected val disposables = CompositeDisposable()

    val verdicts: Observable<RequestState> = _verdicts
    val items: Observable<T> = _items
    val errors: Observable<E> = _errors
    val events: Observable<Packet> = _events

    protected fun pushItem(item: T) {
        _items.onNext(item)
        Logger.debug(TAG, "Item is pushed")
    }

    protected fun pushError(error: E) {
        _errors.onNext(error)
        Logger.debug(TAG, "Error is occurred")
    }

    protected fun pushEvent(event: Packet) {
        _events.onNext(event)
        Logger.debug(TAG, "Event is happened")
    }

//    protected fun pushVerdict(verdict: RequestState) {
//        _verdicts.onNext(verdict)
//        Logger.debug(TAG, "Verdict is ${verdict.name}")
//    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}