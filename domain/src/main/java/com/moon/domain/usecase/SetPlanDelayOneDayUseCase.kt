package com.moon.domain.usecase

import com.moon.domain.repository.PlanRepository
import java.util.*
import javax.inject.Inject

class SetPlanDelayOneDayUseCase @Inject constructor(private val repository: PlanRepository) {
    suspend operator fun invoke(planId:Long, newDate: Date) {
        return repository.setPlanDelayOneDayUseCase(planId, newDate)
    }
}