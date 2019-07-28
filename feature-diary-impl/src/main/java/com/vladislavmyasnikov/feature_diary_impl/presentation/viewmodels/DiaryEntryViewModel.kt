package com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels

import com.vladislavmyasnikov.core_components.components.GeneralViewModel
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.FullDiaryEntry
import io.reactivex.schedulers.Schedulers

class DiaryEntryViewModel(private val repository: DiaryRepository) : GeneralViewModel<FullDiaryEntry>() {

    lateinit var entry: FullDiaryEntry
    private set

    fun fetchFullEntry(id: Long) {
        if (!isLoading) {
            isLoading = true
            progressEmitter.onNext(true)
            disposables.add(repository.fetchFullEntry(id)
                    .doFinally {
                        progressEmitter.onNext(false)
                        isLoading = false
                    }
                    .subscribeOn(Schedulers.io())
                    .subscribe({ item ->
                        entry = item
                        itemEmitter.onNext(item)
                    }, { error ->
                        errorEmitter.onNext(error)//(ExceptionMapper.map(error))
                    })
            )
        }
    }
}
