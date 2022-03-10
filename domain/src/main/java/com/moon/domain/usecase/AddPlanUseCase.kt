package com.moon.domain.usecase

import com.moon.domain.model.PlanModel
import com.moon.domain.repository.PlanRepository
import javax.inject.Inject

class AddPlanUseCase @Inject constructor(private val repository: PlanRepository) {
    suspend operator fun invoke(plan: PlanModel) {
        return repository.addPlan(plan)
    }
}