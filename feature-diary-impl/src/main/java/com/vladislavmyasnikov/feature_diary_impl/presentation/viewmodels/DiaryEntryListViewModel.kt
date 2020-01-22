package com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels

import com.vladislavmyasnikov.common.legacy.GeneralViewModel
import com.vladislavmyasnikov.common.utils.Logger
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryEntryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.ShortDiaryEntry
import io.reactivex.schedulers.Schedulers

class DiaryEntryListViewModel(private val repository: DiaryEntryRepository) : GeneralViewModel<Int>() {

    var mode: Int = NORMAL_MODE

    lateinit var entries: List<ShortDiaryEntry>
        private set

    init {
        fetchShortEntries()
    }

    private fun fetchShortEntries() {
        Logger.debug(TAG, "Entries fetching: REQUEST")
        disposables.add(repository.fetchShortEntries()
                .subscribeOn(Schedulers.io())
                .subscribe({ item ->
                    entries = item
                    itemEmitter.onNext(LOADED_REQUEST_RESULT)
                    Logger.debug(TAG, "Entries fetching: SUCCESS, Amount: ${item.size}")
                }, { error ->
                    errorEmitter.onNext(error)//(ExceptionMapper.map(error))
                    Logger.debug(TAG, "Entries fetching: ERROR, Cause: $error")
                })
        )
    }

    fun deleteEntriesByIDs(ids: List<Long>) {
        Logger.debug(TAG, "Entries deleting: REQUEST")
        if (!isLoading) {
            progressEmitter.onNext(true)
            isLoading = true
            Logger.debug(TAG, "Entries deleting: PROCESSING IS STARTED")

            disposables.add(repository.deleteEntriesByIDs(ids)
                    .doFinally {
                        progressEmitter.onNext(false)
                        isLoading = false
                        Logger.debug(TAG, "Entries deleting: PROCESSING IS COMPLETED")
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