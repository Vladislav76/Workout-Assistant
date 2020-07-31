package com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.BaseVM
import com.vladislavmyasnikov.common.arch.Event
import com.vladislavmyasnikov.feature_diary_impl.domain.repository.DiaryEntryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.entity.ShortDiaryEntry
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.reflect.KClass

class DiaryEntryListVM @Inject constructor(private val repository: DiaryEntryRepository) : BaseVM<List<ShortDiaryEntry>, Throwable>() {

    private var sourceItems = emptyList<ShortDiaryEntry>()
    private val selectedItemIDs = mutableListOf<Long>()

    var isSelectMode = false
        set(value) {
            if (field != value) {
                pushEvent(Event.SelectionModeChangeEvent(value))
                pushEvent(Event.SelectedItemAmountChangeEvent(if (value) 0 else -1))
                if (!value) selectedItemIDs.clear()
                field = value
            }
        }

    fun fetch() {
        disposables.add(
                repository.fetchShortEntries()
                        .subscribeOn(Schedulers.io())
                        .subscribe({ item ->
                            pushItem(item)
                            sourceItems = item
                        }, { error ->
                            pushError(error)
                        })
        )
    }

    fun delete() {
        val selectedItemIDsCopy = selectedItemIDs.toList()

        sourceItems = sourceItems.filter { item -> !selectedItemIDs.contains(item.id) }
        pushItem(sourceItems)
        isSelectMode = false

        disposables.add(
                repository.deleteEntriesByIDs(selectedItemIDsCopy)
                        .subscribeOn(Schedulers.io())
                        .subscribe({}, { error ->
                            pushError(error)
                        })
        )

    }

    fun select(id: Long) {
        if (!selectedItemIDs.remove(id)) selectedItemIDs.add(id)
        pushEvent(Event.SelectedItemAmountChangeEvent(selectedItemIDs.size))
    }

    fun trigger(eventClass: KClass<out Event>) {
        when (eventClass) {
            Event.SelectedItemAmountChangeEvent::class -> pushEvent(Event.SelectedItemAmountChangeEvent(if (isSelectMode) selectedItemIDs.size else -1))
            Event.SelectionModeChangeEvent::class -> pushEvent(Event.SelectionModeChangeEvent(isSelectMode))
        }
    }
}