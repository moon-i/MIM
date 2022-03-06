package com.moon.data.repo

import com.moon.data.room.database.MIMRoomDatabase
import com.moon.data.mapper.PlanMapper
import com.moon.domain.DataResult
import com.moon.domain.model.PlanModel
import com.moon.domain.repository.PlanRepository
import kotlinx.coroutines.flow.collect
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class PlanRepositoryImpl @Inject constructor(private val database: MIMRoomDatabase) : PlanRepository {
    override suspend fun getPlanList(date: Date): DataResult<List<PlanModel>> {
        return try {
            var returnData: List<PlanModel> = mutableListOf()
            database.planDao().getPlanListThatDate(date).collect { list ->
                returnData = PlanMapper.mapEntityToModel(list)
            }
            DataResult.Success(returnData)
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }
}