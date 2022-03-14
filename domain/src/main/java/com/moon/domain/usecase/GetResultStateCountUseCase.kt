package com.moon.domain.usecase

import com.moon.domain.model.StatisticsTotalModel
import com.moon.domain.repository.StatisticsRepository
import java.util.*
import javax.inject.Inject

class GetResultStateCountUseCase @Inject constructor(private val repository: StatisticsRepository) {
    suspend operator fun invoke(startDate: Date, endDate: Date): StatisticsTotalModel {
        return repository.getResultStateCount(startDate, endDate)
    }
}