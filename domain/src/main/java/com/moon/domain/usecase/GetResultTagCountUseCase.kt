package com.moon.domain.usecase

import com.moon.domain.model.TagStatisticsModel
import com.moon.domain.repository.StatisticsRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class GetResultTagCountUseCase @Inject constructor(private val repository: StatisticsRepository) {
    operator fun invoke(startDate: Date, endDate: Date): Flow<List<TagStatisticsModel>> {
        return repository.getResultTagCount(startDate, endDate)
    }
}