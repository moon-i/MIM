package com.moon.domain.repository

import com.moon.domain.model.PlanModel
import com.moon.domain.model.PlanState
import kotlinx.coroutines.flow.Flow
import java.util.*

interface PlanRepository {
    suspend fun addPlan(plan: PlanModel)
    suspend fun deletePlan(plan: PlanModel)
    suspend fun setPlanState(planId: Long, newState: PlanState)
    suspend fun setPlanStateBeforeToday(date: Date)
    suspend fun setPlanDelayOneDayUseCase(planId: Long, newDate: Date)
    fun getPlanListThatDate(date: Date): Flow<List<PlanModel>>
}