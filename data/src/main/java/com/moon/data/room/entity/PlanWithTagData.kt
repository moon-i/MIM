package com.moon.data.room.entity

import androidx.room.ColumnInfo
import java.util.*

data class PlanWithTagData(
    @ColumnInfo(name = "plan_id") val planId: Int,
    @ColumnInfo(name = "plan_name") val planName: String,
    @ColumnInfo(name = "plan_date") val planDate: Date,
    @ColumnInfo(name = "plan_state") val planState: String,
    @ColumnInfo(name = "plan_tag_id") val planTagId: Int? = null,
    @ColumnInfo(name = "tag_name") val tagName: String? = null,
    @ColumnInfo(name = "tag_color") val tagColor: String? = null,
)