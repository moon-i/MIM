package com.moon.domain.usecase

import com.moon.domain.model.PlanState
import com.moon.domain.repository.PlanRepository
import javax.inject.Inject

class SetPlanStateUseCase @Inject constructor(private val repository: PlanRepository) {
    suspend operator fun invoke(planId: Long, state: PlanState) {
        return repository.setPlanState(planId, state)
    }
}