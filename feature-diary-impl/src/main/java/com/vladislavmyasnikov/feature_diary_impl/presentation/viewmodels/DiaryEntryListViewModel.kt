package com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels

import com.vladislavmyasnikov.core_components.components.GeneralViewModel
import com.vladislavmyasnikov.core_utils.utils.utils.Logger
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.ShortDiaryEntry
import io.reactivex.schedulers.Schedulers

class DiaryEntryListViewModel(private val repository: DiaryRepository) : GeneralViewModel<Int>() {

    var mode: Int = NORMAL_MODE
    lateinit var entries: List<ShortDiaryEntry>
        private set

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
                        entries = item
                        itemEmitter.onNext(LOADED_REQUEST_RESULT)
                    }, { error ->
                        errorEmitter.onNext(error)//(ExceptionMapper.map(error))
                    })
            )
        }
    }

    fun deleteEntriesByIDs(ids: List<Long>) {
        if (!isLoading) {
            isLoading = true
            progressEmitter.onNext(true)
            disposables.add(repository.deleteEntriesByIDs(ids)
                    .doFinally {
                        progressEmitter.onNext(false)
                        isLoading = false
                    }
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        itemEmitter.onNext(DELETED_REQUEST_RESULT)
                        Logger.debug(TAG, "Entries deleting: SUCCESS; Amount: ${ids.size}")
                    }, { error ->
                        errorEmitter.onNext(error)//(ExceptionMapper.map(error))
                        Logger.debug(TAG, "Entries deleting: ERROR; Cause: $error")
                    })
            )
        }
    }

    companion object {
        const val NORMAL_MODE = 1
        const val DELETE_MODE = 2
        private const val TAG = "DIARY_ENTRY_LIST_VM"
    }
}