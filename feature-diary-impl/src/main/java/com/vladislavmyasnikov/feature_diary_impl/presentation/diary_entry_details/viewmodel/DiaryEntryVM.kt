package com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_details.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.BaseVM
import com.vladislavmyasnikov.feature_diary_api.domain.entity.DiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.domain.repository.DiaryEntryRepository
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DiaryEntryVM @Inject constructor(private val repository: DiaryEntryRepository) : BaseVM<DiaryEntry, Throwable>() {

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