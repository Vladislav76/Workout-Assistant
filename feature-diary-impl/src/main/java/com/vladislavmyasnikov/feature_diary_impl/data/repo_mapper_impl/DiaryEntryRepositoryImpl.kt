package com.vladislavmyasnikov.feature_diary_impl.data.repo_mapper_impl

import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.feature_diary_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryEntryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.FullDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.domain.ShortDiaryEntry
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class DiaryEntryRepositoryImpl @Inject constructor(private val localDataSource: LocalDatabase) : DiaryEntryRepository {

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
        return localDataSource.diaryDao().deleteEntriesByIDs(ids)
    }
}