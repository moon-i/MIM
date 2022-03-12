package com.moon.data.mapper

import com.moon.data.room.entity.ResultStateCountData
import com.moon.data.room.entity.ResultTagCountData
import com.moon.domain.model.StatisticsTotalModel
import com.moon.domain.model.TagStatisticsModel

object StatisticsMapper {
    fun mapEntityToStatisticsTotalModel(entity: ResultStateCountData): StatisticsTotalModel {
        return StatisticsTotalModel(
            entity.totalCount,
            entity.successCount,
            entity.failCount,
            entity.cancelCount
        )
    }

    fun mapEntityListToTagStatisticsModelList(entityList: List<ResultTagCountData>): List<TagStatisticsModel> {
        val resultList: MutableList<TagStatisticsModel> = mutableListOf()
        entityList.map { entity ->
            resultList.add(TagStatisticsModel(
                entity.planTagId ?: -1,
                entity.tagName ?: "기타",
                entity.tagColor ?: "#E0E0E0",
                entity.count,
                entity.successCount,
            ))
        }
        return resultList
    }
}