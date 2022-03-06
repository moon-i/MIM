package com.moon.domain.repository

import com.moon.domain.DataResult
import com.moon.domain.model.PlanModel
import java.util.*

interface PlanRepository {
    suspend fun getPlanList(date: Date): DataResult<List<PlanModel>>
}