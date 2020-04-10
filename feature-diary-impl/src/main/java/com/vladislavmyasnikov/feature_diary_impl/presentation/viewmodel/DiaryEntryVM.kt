package com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodel

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_diary_impl.domain.repository.DiaryEntryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.model.FullDiaryEntry
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DiaryEntryVM @Inject constructor(private val repository: DiaryEntryRepository) : BaseViewModel<FullDiaryEntry, Throwable>() {

    fun fetch(id: Long) {
        disposables.add(
                repository.fetchFullEntry(id)
                        .subscribeOn(Schedulers.io())
                        .subscribe({ item ->
                            pushItem(item)
                        }, { error ->
                            pushError(error)
                        })
        )
    }
}