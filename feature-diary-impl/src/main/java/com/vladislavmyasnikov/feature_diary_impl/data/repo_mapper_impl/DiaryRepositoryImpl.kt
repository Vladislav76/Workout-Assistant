package com.vladislavmyasnikov.feature_diary_impl.data.repo_mapper_impl

import com.vladislavmyasnikov.feature_diary_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.ShortDiaryEntry
import io.reactivex.Observable
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(private val localDataSource: LocalDatabase) : DiaryRepository {

    override fun fetchShortEntries(): Observable<List<ShortDiaryEntry>> {
        return localDataSource.diaryDao().loadShortEntries().map(EntityToModelShortDiaryEntryMapper::map)
    }
}