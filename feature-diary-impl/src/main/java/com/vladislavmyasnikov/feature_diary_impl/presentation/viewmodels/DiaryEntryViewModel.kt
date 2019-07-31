package com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels

import com.vladislavmyasnikov.core_components.components.GeneralViewModel
import com.vladislavmyasnikov.core_utils.utils.utils.Logger
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.FullDiaryEntry
import io.reactivex.schedulers.Schedulers

class DiaryEntryViewModel(private val repository: DiaryRepository) : GeneralViewModel<RequestResult>() {

    lateinit var entry: FullDiaryEntry
    private set

    fun fetchFullEntry(id: Long) {
        if (!isLoading) {
            Logger.debug(TAG, "Entry fetching: REQUEST")

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
                        itemEmitter.onNext(RequestResult.LOADED)
                        Logger.debug(TAG, "Entry fetching: SUCCESS")
                    }, { error ->
                        errorEmitter.onNext(error)//(ExceptionMapper.map(error))
                        Logger.debug(TAG, "Entry fetching: ERROR; Cause: $error")
                    })
            )
        }
    }

    fun saveFullEntry() {
        if (!isLoading) {
            isLoading = true
            progressEmitter.onNext(true)
            disposables.add(repository.saveFullEntry(entry)
                    .doFinally {
                        progressEmitter.onNext(false)
                        isLoading = false
                    }
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        itemEmitter.onNext(RequestResult.SAVED)
                        Logger.debug(TAG, "Entry saving: SUCCESS")
                    }, { error ->
                        errorEmitter.onNext(error)//(ExceptionMapper.map(error))
                        Logger.debug(TAG, "Entry saving: ERROR; Cause: $error")
                    })
            )
        }
    }

    companion object {
        private const val TAG = "DIARY_ENTRY_VM"
    }
}

enum class RequestResult {
    LOADED, SAVED
}
