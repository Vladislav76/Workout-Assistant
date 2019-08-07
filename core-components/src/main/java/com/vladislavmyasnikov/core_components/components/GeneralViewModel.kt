package com.vladislavmyasnikov.core_components.components

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

open class GeneralViewModel<T> : ViewModel() {

    protected val disposables = CompositeDisposable()
    protected val progressEmitter: BehaviorSubject<Boolean> = BehaviorSubject.create()
    protected val itemEmitter: BehaviorSubject<T> = BehaviorSubject.create()
    protected val errorEmitter: PublishSubject<Throwable> = PublishSubject.create()
    protected var isLoading = false

    val processingState: Observable<Boolean> = progressEmitter
    val items: Observable<T> = itemEmitter
    val errors: Observable<Throwable> = errorEmitter

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    companion object {
        const val LOADED_REQUEST_RESULT = 1
        const val SAVED_REQUEST_RESULT = 2
        const val DELETED_REQUEST_RESULT = 3
    }
}