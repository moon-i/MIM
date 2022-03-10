package com.moon.data.repo

import com.moon.data.room.database.MIMRoomDatabase
import com.moon.data.mapper.PlanMapper
import com.moon.domain.model.PlanModel
import com.moon.domain.model.PlanState
import com.moon.domain.repository.PlanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class PlanRepositoryImpl @Inject constructor(private val database: MIMRoomDatabase) :
    PlanRepository {

    override suspend fun addPlan(plan: PlanModel) {
        database.planDao().insert(PlanMapper.mapModelToEntity(plan))
    }

    override suspend fun deletePlan(plan: PlanModel) {
        database.planDao().delete(PlanMapper.mapModelToEntity(plan))
    }

    override suspend fun setPlanState(planId: Long, newState: PlanState) {
        database.planDao().setPlanState(planId, newState.dbVale)
    }

    override suspend fun setPlanDelayOneDayUseCase(planId: Long, newDate: Date) {
        database.planDao().setPlanDelayOneDay(planId, newDate)
    }

    override suspend fun setPlanStateBeforeToday(date: Date) {
        database.planDao().setPlanStateBeforeToday(date)
    }

    override fun getPlanListThatDate(date: Date): Flow<List<PlanModel>> {
        return flow {
            emit(PlanMapper.mapEntityToModelList(database.planDao().getPlanListThatDate(date)))
        }
    }
}