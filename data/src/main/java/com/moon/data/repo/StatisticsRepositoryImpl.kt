package com.moon.data.repo

import com.moon.data.mapper.StatisticsMapper
import com.moon.data.room.database.MIMRoomDatabase
import com.moon.domain.model.StatisticsTotalModel
import com.moon.domain.model.TagStatisticsModel
import com.moon.domain.repository.StatisticsRepository
import java.util.*
import javax.inject.Inject

class StatisticsRepositoryImpl @Inject constructor(private val database: MIMRoomDatabase) :
    StatisticsRepository {
    override suspend fun getResultStateCount(startDate: Date, endDate: Date): StatisticsTotalModel {
        return StatisticsMapper.mapEntityToStatisticsTotalModel(
            database.statisticsDao().getResultStateCount(startDate, endDate)
        )
    }

    override suspend fun getResultTagCount(
        startDate: Date,
        endDate: Date
    ): List<TagStatisticsModel> {
        return StatisticsMapper.mapEntityListToTagStatisticsModelList(
            database.statisticsDao().getResultTagCount(startDate, endDate)
        )
    }
}
