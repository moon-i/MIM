package com.moon.domain.model

data class TagModel(
    val tagId: Int,
    val tagName: String,
    val tagColor: String,
    val tagState: Boolean = true
)