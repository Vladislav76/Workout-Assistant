package com.vladislavmyasnikov.feature_diary_impl.data.repository

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.feature_diary_api.DiaryInteractor
import com.vladislavmyasnikov.feature_diary_api.domain.entity.FullDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.data.db.*
import com.vladislavmyasnikov.feature_diary_impl.domain.repository.DiaryEntryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.entity.ShortDiaryEntry
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import javax.inject.Inject

@PerFeature
class DiaryEntryRepositoryImpl @Inject constructor(private val localDataSource: LocalDatabase) : DiaryEntryRepository, DiaryInteractor {

    override fun fetchShortEntries(): Observable<List<ShortDiaryEntry>> {
        return localDataSource.diaryDao().loadShortEntries().map(EntityToModelShortDiaryEntryMapper::map)
    }

    override fun fetchFullEntry(id: Long): Maybe<FullDiaryEntry> {
        return localDataSource.diaryDao().loadEntryById(id).map(EntityToModelFullDiaryEntryMapper::map)
    }

    override fun saveFullEntry(entry: FullDiaryEntry): Completable {
        return Completable.fromRunnable {
            if (entry.id > 0) {
                localDataSource.diaryDao().updateEntry(ModelToEntityFullDiaryEntryMapper.map(entry))
            } else {
                localDataSource.diaryDao().insertEntry(ModelToEntityFullDiaryEntryMapper.map(entry))
            }
        }
    }

    override fun deleteEntriesByIDs(ids: List<Long>): Completable {
        return localDataSource.diaryDao().deleteEntriesByIDs(ids)
    }

    override fun saveEntry(entry: FullDiaryEntry): Completable {
        return localDataSource.diaryDao().insertEntry(ModelToEntityFullDiaryEntryMapper.map(entry))
    }
}