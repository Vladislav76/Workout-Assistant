package com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels

import com.vladislavmyasnikov.core_components.components.GeneralViewModel
import com.vladislavmyasnikov.core_components.models.TimePoint
import com.vladislavmyasnikov.core_utils.utils.utils.Logger
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.FullDiaryEntry
import io.reactivex.schedulers.Schedulers
import java.util.*

class DiaryEntryViewModel(private val repository: DiaryRepository) : GeneralViewModel<Int>() {

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
                        itemEmitter.onNext(LOADED_REQUEST_RESULT)
                        Logger.debug(TAG, "Entry fetching: SUCCESS")
                    }, { error ->
                        errorEmitter.onNext(error)//(ExceptionMapper.map(error))
                        Logger.debug(TAG, "Entry fetching: ERROR; Cause: $error")
                    }, {
                        val currentDate = Date()
                        val calendar = Calendar.getInstance().apply { time = currentDate }
                        val currentTime = TimePoint(calendar.get(Calendar.HOUR_OF_DAY).toLong(), calendar.get(Calendar.MINUTE).toLong())
                        entry = FullDiaryEntry(0, currentDate, currentTime, currentTime, TimePoint(0), "")
                        itemEmitter.onNext(LOADED_REQUEST_RESULT)
                        Logger.debug(TAG, "Entry fetching: NO FOUND - NEW ENTRY")
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
                        itemEmitter.onNext(SAVED_REQUEST_RESULT)
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