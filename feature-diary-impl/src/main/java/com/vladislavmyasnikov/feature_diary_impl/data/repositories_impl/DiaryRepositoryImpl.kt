package com.vladislavmyasnikov.feature_diary_impl.data.repositories_impl

import com.vladislavmyasnikov.feature_diary_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryEntry
import io.reactivex.Maybe
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(private val localDataSource: LocalDatabase) : DiaryRepository {

    override fun fetchEntries(): Maybe<List<DiaryEntry>> {
        return localDataSource.diaryDao().loadEntries().map(EntityToModelDiaryEntryMapper::map)
    }
}