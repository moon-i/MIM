package com.moon.data.room.entity

import androidx.room.ColumnInfo

data class ResultStateCountData (
    @ColumnInfo(name = "total_count") val totalCount: Int,
    @ColumnInfo(name = "success_count") val successCount: Int,
    @ColumnInfo(name = "fail_count") val failCount: Int,
    @ColumnInfo(name = "waiting_count") val waitingCount: Int,
)
