package com.moon.domain.repository

import com.moon.domain.model.StatisticsTotalModel
import com.moon.domain.model.TagStatisticsModel
import java.util.*

interface StatisticsRepository {
    suspend fun getResultStateCount(startDate: Date, endDate: Date): StatisticsTotalModel
    suspend fun getResultTagCount(startDate: Date, endDate: Date): List<TagStatisticsModel>
}