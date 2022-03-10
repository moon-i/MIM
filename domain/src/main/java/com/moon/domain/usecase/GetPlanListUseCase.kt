package com.moon.domain.usecase

import com.moon.domain.model.PlanModel
import com.moon.domain.repository.PlanRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class GetPlanListUseCase @Inject constructor(private val repository: PlanRepository) {
    operator fun invoke(date: Date): Flow<List<PlanModel>> {
        return repository.getPlanListThatDate(date)
    }
}