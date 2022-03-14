package com.moon.domain.repository

import com.moon.domain.model.TagModel

interface TagRepository {
    suspend fun addTag(tagModel: TagModel)
    suspend fun deleteTag(tagId: Long)
    suspend fun updateTagColor(tagId: Long, tagColor: String)
    suspend fun getTagList(): List<TagModel>
}