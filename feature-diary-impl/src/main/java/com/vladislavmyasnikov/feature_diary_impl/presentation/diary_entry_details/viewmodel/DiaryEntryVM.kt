package com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_details.viewmodel

import com.vladislavmyasnikov.common.arch.viewmodel.SimpleVM
import com.vladislavmyasnikov.common.models.Either
import com.vladislavmyasnikov.feature_diary_api.domain.entity.DiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.domain.usecase.diary_entry_details.GetDiaryEntryUC
import io.reactivex.Completable
import javax.inject.Inject

class DiaryEntryVM @Inject constructor(
        private val getDiaryEntryUC: GetDiaryEntryUC
) : SimpleVM<DiaryEntry>() {

    override fun processRequest(id: Long): Either<Boolean, Completable> {
        return Either.Right(initAsynchronousRequest(getDiaryEntryUC.getDiaryEntryById(id)))
    }
}