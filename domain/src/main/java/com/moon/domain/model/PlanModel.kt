package com.moon.domain.model

import java.util.Date

data class PlanModel(
    val planId: Long,
    val planName: String,
    val planState: String,
    val planDate: Date,
    val planTag: TagModel? = null
)