package com.moon.domain.repository

import com.moon.domain.model.StatisticsTotalModel
import com.moon.domain.model.TagStatisticsModel
import kotlinx.coroutines.flow.Flow
import java.util.*

interface StatisticsRepository {
    fun getResultStateCount(startDate: Date, endDate: Date): Flow<StatisticsTotalModel>
    fun getResultTagCount(startDate: Date, endDate: Date): Flow<List<TagStatisticsModel>>
}