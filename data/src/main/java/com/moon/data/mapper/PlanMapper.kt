package com.moon.data.mapper

import com.moon.data.room.entity.PlanWithTagData
import com.moon.domain.model.PlanModel
import com.moon.domain.model.TagModel

object PlanMapper {
    fun mapEntityToModel(entityList: List<PlanWithTagData>): List<PlanModel> {
        val returnList: MutableList<PlanModel> = mutableListOf()
        entityList.map { entity ->
            returnList.add(
                PlanModel(
                    entity.planId,
                    entity.planName,
                    entity.planState,
                    entity.planDate.toString(),
                    entity.tagName?.let {
                        TagModel(
                            entity.planTagId ?: -1,
                            entity.tagName,
                            entity.tagColor ?: "",
                            true,
                        )
                    } ?: run {
                        null
                    }

                )
            )
        }
        return returnList
    }
}