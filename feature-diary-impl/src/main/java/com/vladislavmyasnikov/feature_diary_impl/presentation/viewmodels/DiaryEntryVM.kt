package com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels

import com.vladislavmyasnikov.common.arch_components.BaseViewModel
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryEntryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.FullDiaryEntry
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DiaryEntryVM @Inject constructor(private val repository: DiaryEntryRepository) : BaseViewModel<FullDiaryEntry, Throwable>() {

    override val label = "DIARY_ENTRY_VM"

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