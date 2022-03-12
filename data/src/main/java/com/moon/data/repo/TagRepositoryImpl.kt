package com.moon.data.repo

import com.moon.data.mapper.TagMapper
import com.moon.data.room.database.MIMRoomDatabase
import com.moon.domain.DataResult
import com.moon.domain.model.TagModel
import com.moon.domain.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(private val database: MIMRoomDatabase) : TagRepository {

    override suspend fun addTag(tagModel: TagModel) {
        database.tagDao().insert(TagMapper.modelToEntity(tagModel))
    }

    override suspend fun deleteTag(tagId: Long): DataResult<Int> {
        return DataResult.Success(database.tagDao().deleteTag(tagId))
    }

    override suspend fun updateTagColor(tagId: Long, tagColor: String): DataResult<Int> {
        return DataResult.Success(database.tagDao().updateTagColor(tagId, tagColor))
    }

    override fun getTagList(): Flow<List<TagModel>> {
        return flow {
            database.tagDao().getTagList().let { list ->
                val returnData: MutableList<TagModel> = mutableListOf()
                list.map { item ->
                    returnData.add(TagMapper.entityToModel(item))
                }
                emit(returnData)
            }
        }
    }
}