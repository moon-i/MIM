package com.moon.domain.repository

import com.moon.domain.DataResult
import com.moon.domain.model.TagModel
import kotlinx.coroutines.flow.Flow

interface TagRepository {
    suspend fun addTag(tagModel: TagModel)
    suspend fun deleteTag(tagId: Long): DataResult<Int>
    suspend fun updateTagColor(tagId: Long, tagColor: String): DataResult<Int>
    fun getTagList(): Flow<List<TagModel>>
}