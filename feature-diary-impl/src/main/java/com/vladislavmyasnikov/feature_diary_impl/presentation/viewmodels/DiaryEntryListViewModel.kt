package com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels

import com.vladislavmyasnikov.core_components.components.GeneralViewModel
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.ShortDiaryEntry
import io.reactivex.schedulers.Schedulers

class DiaryEntryListViewModel(private val repository: DiaryRepository) : GeneralViewModel<List<ShortDiaryEntry>>() {

    fun fetchShortEntries() {
        if (!isLoading) {
            isLoading = true
            progressEmitter.onNext(true)
            disposables.add(repository.fetchShortEntries()
                    .doFinally {
                        progressEmitter.onNext(false)
                        isLoading = false
                    }
                    .subscribeOn(Schedulers.io())
                    .subscribe({ item ->
                        itemEmitter.onNext(item)
                    }, { error ->
                        errorEmitter.onNext(error)//(ExceptionMapper.map(error))
                    })
            )
        }
    }
}
