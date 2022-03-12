package com.moon.data.room.entity

import androidx.room.ColumnInfo

data class ResultTagCountData(
    @ColumnInfo(name = "plan_tag_id") val planTagId: Int? = null,
    @ColumnInfo(name = "tag_name") val tagName: String? = null,
    @ColumnInfo(name = "tag_color") val tagColor: String? = null,
    @ColumnInfo(name = "count") val count: Int,
    @ColumnInfo(name = "success_count") val successCount: Int,
)