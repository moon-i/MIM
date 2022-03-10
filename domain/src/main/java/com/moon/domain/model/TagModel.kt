package com.moon.domain.model

import java.io.Serializable

data class TagModel(
    var tagId: Long,
    var tagName: String,
    var tagColor: String,
    var tagState: Boolean = true,
    var tagSelected: Boolean = false
) : Serializable {
    override fun equals(other: Any?): Boolean {
        return false
    }
}

