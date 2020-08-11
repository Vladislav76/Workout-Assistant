package com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_list.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.feature_diary_impl.domain.entity.ShortDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.domain.usecase.diary_entry_list.GetDiaryEntryListUC
import io.reactivex.Completable
import javax.inject.Inject

class DiaryEntryListVM @Inject constructor(
        private val getDiaryEntryListUC: GetDiaryEntryListUC
) : SimpleVM<List<ShortDiaryEntry>>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(getDiaryEntryListUC.getAllEntries()))
    }
}