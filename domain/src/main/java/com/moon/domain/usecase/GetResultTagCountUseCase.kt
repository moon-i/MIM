package com.moon.domain.usecase

import com.moon.domain.model.TagStatisticsModel
import com.moon.domain.repository.StatisticsRepository
import java.util.*
import javax.inject.Inject

class GetResultTagCountUseCase @Inject constructor(private val repository: StatisticsRepository) {
    suspend operator fun invoke(startDate: Date, endDate: Date): List<TagStatisticsModel> {
        return repository.getResultTagCount(startDate, endDate)
    }
}