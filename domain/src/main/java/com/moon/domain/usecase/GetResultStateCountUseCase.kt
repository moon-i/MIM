package com.moon.domain.usecase

import com.moon.domain.model.StatisticsTotalModel
import com.moon.domain.repository.StatisticsRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class GetResultStateCountUseCase @Inject constructor(private val repository: StatisticsRepository) {
    operator fun invoke(startDate: Date, endDate: Date): Flow<StatisticsTotalModel> {
        return repository.getResultStateCount(startDate, endDate)
    }
}