package com.moon.data.repo

import com.moon.data.mapper.StatisticsMapper
import com.moon.data.room.database.MIMRoomDatabase
import com.moon.domain.model.StatisticsTotalModel
import com.moon.domain.model.TagStatisticsModel
import com.moon.domain.repository.StatisticsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class StatisticsRepositoryImpl @Inject constructor(private val database: MIMRoomDatabase) :
    StatisticsRepository {
    override fun getResultStateCount(startDate: Date, endDate: Date): Flow<StatisticsTotalModel> {
        return flow {
            emit(
                StatisticsMapper.mapEntityToStatisticsTotalModel(
                    database.statisticsDao().getResultStateCount(startDate, endDate)
                )
            )
        }
    }

    override fun getResultTagCount(startDate: Date, endDate: Date): Flow<List<TagStatisticsModel>> {
        return flow {
            emit(
                StatisticsMapper.mapEntityListToTagStatisticsModelList(
                    database.statisticsDao().getResultTagCount(startDate, endDate)
                )
            )
        }
    }
}