package com.moon.data.mapper

import com.moon.data.room.entity.TagEntity
import com.moon.domain.model.TagModel

object TagMapper {
    fun modelToEntity(model: TagModel): TagEntity {
        return TagEntity(
            tagName = model.tagName,
            tagColor = model.tagColor,
            tagState = model.tagState
        )
    }

    fun entityToModel(entity: TagEntity): TagModel {
        return TagModel(
            entity.tagId,
            entity.tagName,
            entity.tagColor,
            entity.tagState
        )
    }
}