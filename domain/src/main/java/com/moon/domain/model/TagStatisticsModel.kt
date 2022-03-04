package com.moon.domain.model

data class TagStatisticsModel(
    val tagId: Int,
    val tagName: String,
    val tagColor: String,
    val totalCount: Int = 0,
    val successCount: Int = 0
)