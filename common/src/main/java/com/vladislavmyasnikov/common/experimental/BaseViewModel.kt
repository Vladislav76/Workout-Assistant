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

    private val _verdicts: BehaviorSubject<RequestState> = BehaviorSubject.create()
    private val _items: BehaviorSubject<T> = BehaviorSubject.create()
    private val _errors: PublishSubject<E> = PublishSubject.create()
    private var isLoading = false
    protected val disposables = CompositeDisposable()

    val verdicts: Observable<RequestState> = _verdicts
    val items: Observable<T> = _items
    val errors: Observable<E> = _errors

    protected fun pushItem(item: T) {
        _items.onNext(item)
        Logger.debug(TAG, "Item is pushed")
    }

    protected fun pushError(error: E) {
        _errors.onNext(error)
        Logger.debug(TAG, "Error is occurred")
    }

    protected fun pushVerdict(verdict: RequestState) {
        _verdicts.onNext(verdict)
        Logger.debug(TAG, "Verdict is ${verdict.name}")
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}