package com.moon.data.room.dao

import androidx.room.*
import com.moon.data.room.entity.TagEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {
    // 1. 태그 insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tag: TagEntity)

    // 2. 태그 delete
    @Query("update tag_table set tag_state = 0 where tag_id= :tagId")
    suspend fun deleteTag(tagId: Long): Int

    // 3. 태그 색상 수정
    @Query("update tag_table set tag_color = :color where tag_id= :tagId")
    suspend fun updateTagColor(tagId: Long, color: String): Int

    // 4. 태그 조회
    @Query("select * from tag_table where tag_state= 1")
    suspend fun getTagList(): List<TagEntity>
}