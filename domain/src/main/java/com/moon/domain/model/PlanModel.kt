package com.moon.domain.model

data class PlanModel(
    val planId: Long,
    val planName: String,
    val planState: String,
    val planDate: String,
    val planTag: TagModel? = null
)