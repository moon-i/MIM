package com.moon.domain.usecase

import com.moon.domain.DataResult
import com.moon.domain.model.PlanModel
import com.moon.domain.repository.PlanRepository
import java.util.*
import javax.inject.Inject

class GetPlanListUseCase @Inject constructor(private val repository: PlanRepository) {
    suspend operator fun invoke(date: Date): DataResult<List<PlanModel>> {
        return repository.getPlanList(date)
    }
}