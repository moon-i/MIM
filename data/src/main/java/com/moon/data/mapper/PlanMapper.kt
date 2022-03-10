package com.moon.data.mapper

import com.moon.data.room.entity.PlanEntity
import com.moon.data.room.entity.PlanWithTagData
import com.moon.domain.model.PlanModel
import com.moon.domain.model.PlanState
import com.moon.domain.model.TagModel

object PlanMapper {
    fun mapEntityToModelList(entityList: List<PlanWithTagData>): List<PlanModel> {
        val returnList: MutableList<PlanModel> = mutableListOf()
        entityList.map { entity ->
            returnList.add(
                PlanModel(
                    entity.planId,
                    entity.planName,
                    mapStringToState(entity.planState),
                    entity.planDate,
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

    fun mapModelToEntity(model: PlanModel): PlanEntity {
        return PlanEntity(
            model.planId,
            model.planName,
            model.planDate,
            model.planState.dbVale,
            model.planTag?.tagId
        )
    }

    private fun mapStringToState(stateString: String): PlanState {
        return when (stateString) {
            PlanState.SUCCESS.dbVale -> PlanState.SUCCESS
            PlanState.FAIL.dbVale -> PlanState.FAIL
            PlanState.CANCEL.dbVale -> PlanState.CANCEL
            else -> PlanState.WAITING
        }
    }
}