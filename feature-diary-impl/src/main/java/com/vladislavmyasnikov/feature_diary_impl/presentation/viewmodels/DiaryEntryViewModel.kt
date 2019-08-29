package com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels

import com.vladislavmyasnikov.common.components.GeneralViewModel
import com.vladislavmyasnikov.common.models.TimePoint
import com.vladislavmyasnikov.common.utils.Logger
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryEntryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.FullDiaryEntry
import io.reactivex.schedulers.Schedulers
import java.util.*

class DiaryEntryViewModel(private val repository: DiaryEntryRepository) : GeneralViewModel<Int>() {

    var wasFirstFetchRequest = false
        private set

    lateinit var entry: FullDiaryEntry
        private set

    fun fetchFullEntry(id: Long) {
        Logger.debug(TAG, "Entry fetching: REQUEST")
        if (!isLoading) {
            wasFirstFetchRequest = true
            progressEmitter.onNext(true)
            isLoading = true
            Logger.debug(TAG, "Entry fetching: PROCESSING IS STARTED")

            disposables.add(repository.fetchFullEntry(id)
                    .doFinally {
                        progressEmitter.onNext(false)
                        isLoading = false
                        Logger.debug(TAG, "Entry fetching: PROCESSING IS COMPLETED")
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
                        Logger.debug(TAG, "Entry fetching: NO FOUND, New entry is created")
                    })
            )
        }
    }

    fun saveFullEntry() {
        Logger.debug(TAG, "Entry saving: REQUEST")
        if (!isLoading) {
            progressEmitter.onNext(true)
            isLoading = true
            Logger.debug(TAG, "Entry saving: PROCESSING IS STARTED")

            disposables.add(repository.saveFullEntry(entry!!)
                    .doFinally {
                        progressEmitter.onNext(false)
                        isLoading = false
                        Logger.debug(TAG, "Entry saving: PROCESSING IS COMPLETED")
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