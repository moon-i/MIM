package com.moon.domain.usecase

import com.moon.domain.repository.PlanRepository
import java.util.*
import javax.inject.Inject

class SetPlanStateBeforeTodayUseCase @Inject constructor(private val repository: PlanRepository) {
    suspend operator fun invoke(date: Date) {
        return repository.setPlanStateBeforeToday(date)
    }
}