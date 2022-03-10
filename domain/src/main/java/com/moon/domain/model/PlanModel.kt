package com.moon.domain.model

import java.util.Date

data class PlanModel(
    var planId: Long,
    var planName: String,
    var planState: PlanState,
    var planDate: Date,
    var planTag: TagModel? = null
)