package com.moon.data.repo

import com.moon.data.mapper.TagMapper
import com.moon.data.room.database.MIMRoomDatabase
import com.moon.domain.model.TagModel
import com.moon.domain.repository.TagRepository
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(private val database: MIMRoomDatabase) : TagRepository {

    override suspend fun addTag(tagModel: TagModel) {
        database.tagDao().insert(TagMapper.modelToEntity(tagModel))
    }

    override suspend fun deleteTag(tagId: Long) {
        database.tagDao().deleteTag(tagId)
    }

    override suspend fun updateTagColor(tagId: Long, tagColor: String) {
        database.tagDao().updateTagColor(tagId, tagColor)
    }

    override suspend fun getTagList(): List<TagModel> {
        val returnData: MutableList<TagModel> = mutableListOf()
        database.tagDao().getTagList().let { list ->
            list.map { item ->
                returnData.add(TagMapper.entityToModel(item))
            }
        }
        return returnData
    }
}