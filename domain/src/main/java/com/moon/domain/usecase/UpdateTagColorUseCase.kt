package com.moon.domain.usecase

import com.moon.domain.DataResult
import com.moon.domain.repository.TagRepository
import javax.inject.Inject

class UpdateTagColorUseCase @Inject constructor(private val repository: TagRepository) {
    suspend operator fun invoke(tagId: Long, color: String): DataResult<Int> {
        return repository.updateTagColor(tagId, color)
    }
}