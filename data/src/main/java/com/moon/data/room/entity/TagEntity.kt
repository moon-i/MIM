package com.moon.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag_table")
data class TagEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "tag_id") val tagId: Long = 0,
    @ColumnInfo(name = "tag_name") val tagName: String,
    @ColumnInfo(name = "tag_color") val tagColor: String,
    @ColumnInfo(name = "tag_state") val tagState: Boolean = true
)