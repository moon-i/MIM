package com.moon.domain.model

data class PlanModel(
    val planId: Int,
    val planName: String,
    val planState: String,
    val planDate: String,
    val planTag: TagModel? = null
)