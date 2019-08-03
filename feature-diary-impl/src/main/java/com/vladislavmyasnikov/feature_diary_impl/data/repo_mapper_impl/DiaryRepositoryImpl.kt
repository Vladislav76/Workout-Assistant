package com.vladislavmyasnikov.feature_diary_impl.data.repo_mapper_impl

import com.vladislavmyasnikov.feature_diary_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.FullDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.domain.ShortDiaryEntry
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(private val localDataSource: LocalDatabase) : DiaryRepository {

    override fun fetchShortEntries(): Single<List<ShortDiaryEntry>> {
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
        return Completable.fromRunnable {
            localDataSource.diaryDao().deleteEntriesByIDs(ids)
        }
    }
}