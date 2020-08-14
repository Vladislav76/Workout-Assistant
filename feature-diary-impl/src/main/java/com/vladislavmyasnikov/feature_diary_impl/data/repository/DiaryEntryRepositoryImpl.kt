package com.vladislavmyasnikov.feature_diary_impl.data.repository

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.feature_diary_api.DiaryInteractor
import com.vladislavmyasnikov.feature_diary_api.domain.entity.DiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.data.db.*
import com.vladislavmyasnikov.feature_diary_impl.data.db.entity.ShortDiaryEntryEntity
import com.vladislavmyasnikov.feature_diary_impl.domain.repository.DiaryEntryRepository
import com.vladislavmyasnikov.feature_diary_impl.domain.entity.ShortDiaryEntry
import com.vladislavmyasnikov.feature_workout_library_api.WorkoutLibraryInteractor
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@PerFeature
class DiaryEntryRepositoryImpl @Inject constructor(
        private val localDataSource: LocalDatabase,
        private val workoutLibraryInteractor: WorkoutLibraryInteractor
) : DiaryEntryRepository, DiaryInteractor {

    private val workoutNameCache = mutableMapOf<Long,String>()

    override fun fetchAllEntries(): Observable<List<ShortDiaryEntry>> {
        return localDataSource.diaryDao()
                .loadAllEntries()
                .map { entries ->
                    val newIds = mutableListOf<Long>()
                    for (entry in entries) {
                        if (!workoutNameCache.containsKey(entry.workoutId)) {
                            newIds.add(entry.workoutId)
                        }
                    }
                    if (newIds.isNotEmpty()) {
                        val newNames = workoutLibraryInteractor.fetchWorkoutNamesByIds(newIds)
                        for (i in 0 until newIds.size) {
                            workoutNameCache[newIds[i]] = newNames[i]
                        }
                    }
                    mutableListOf<ShortDiaryEntry>().apply {
                        for (entry in entries)
                            add(map(entry))
                    }
                }
    }

    override fun fetchEntryById(id: Long): Single<DiaryEntry> {
        return localDataSource.diaryDao()
                .loadEntryById(id)
                .map(EntityToModelFullDiaryEntryMapper::map)
    }

    override fun saveEntry(entry: DiaryEntry): Completable {
        return Completable.fromRunnable {
            if (entry.id > 0) {
                localDataSource.diaryDao()
                        .updateEntry(ModelToEntityFullDiaryEntryMapper.map(entry))
            } else {
                localDataSource.diaryDao()
                        .insertEntry(ModelToEntityFullDiaryEntryMapper.map(entry))
            }
        }
    }

    override fun deleteEntriesByIds(ids: List<Long>): Completable {
        return localDataSource.diaryDao()
                .deleteEntriesByIds(ids)
    }

    private fun map(value: ShortDiaryEntryEntity): ShortDiaryEntry {
        return ShortDiaryEntry(value.id, value.date, value.duration, value.workoutProductivity, workoutNameCache[value.workoutId] ?: "NOT FOUND")
    }
}