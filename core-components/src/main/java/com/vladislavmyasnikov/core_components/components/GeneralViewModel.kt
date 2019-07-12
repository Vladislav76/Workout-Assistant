package com.vladislavmyasnikov.core_components.components

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

open class GeneralViewModel<T> : ViewModel() {

    protected val disposables = CompositeDisposable()
    protected val progressEmitter = BehaviorSubject.create<Boolean>()
    protected val itemEmitter = BehaviorSubject.create<T>()
    protected val errorEmitter = PublishSubject.create<Throwable>()
    protected var isLoading = false

    val loadingState: Observable<Boolean> = progressEmitter
    val items: Observable<T> = itemEmitter
    val errors: Observable<Throwable> = errorEmitter

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}