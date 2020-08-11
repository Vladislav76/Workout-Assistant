package com.vladislavmyasnikov.feature_diary_impl.data.repository

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.feature_diary_api.DiaryInteractor
import com.vladislavmyasnikov.feature_diary_api.domain.entity.DiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.data.db.*
import com.vladislavmyasnikov.feature_diary_impl.domain.repository.DiaryEntryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.entity.ShortDiaryEntry
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class DiaryEntryRepositoryImpl @Inject constructor(private val localDataSource: LocalDatabase) : DiaryEntryRepository, DiaryInteractor {

    override fun fetchAllEntries(): Observable<List<ShortDiaryEntry>> {
        return localDataSource.diaryDao().loadAllEntries().map(EntityToModelShortDiaryEntryMapper::map)
    }

    override fun fetchEntryById(id: Long): Single<DiaryEntry> {
        return localDataSource.diaryDao().loadEntryById(id).map(EntityToModelFullDiaryEntryMapper::map)
    }

    override fun saveEntry(entry: DiaryEntry): Completable {
        return Completable.fromRunnable {
            if (entry.id > 0) {
                localDataSource.diaryDao().updateEntry(ModelToEntityFullDiaryEntryMapper.map(entry))
            } else {
                localDataSource.diaryDao().insertEntry(ModelToEntityFullDiaryEntryMapper.map(entry))
            }
        }
    }

    override fun deleteEntriesByIds(ids: List<Long>): Completable {
        return localDataSource.diaryDao().deleteEntriesByIds(ids)
    }
}