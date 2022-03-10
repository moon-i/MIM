package com.moon.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "plan_table")
data class PlanEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "plan_id") val planId: Long,
    @ColumnInfo(name = "plan_name") val planName: String,
    @ColumnInfo(name = "plan_date") val planDate: Date,
    @ColumnInfo(name = "plan_state") val planState: String,
    @ColumnInfo(name = "plan_tag_id") val planTagId: Long? = null,
)