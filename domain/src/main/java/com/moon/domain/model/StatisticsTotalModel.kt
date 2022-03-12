package com.moon.domain.model

data class StatisticsTotalModel(
    val totalCont: Int,
    val successCount: Int,
    val failCount: Int,
    val cancelCount: Int,
)