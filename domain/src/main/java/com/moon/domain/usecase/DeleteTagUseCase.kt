package com.moon.domain.usecase

import com.moon.domain.repository.TagRepository
import javax.inject.Inject

class DeleteTagUseCase @Inject constructor(private val repository: TagRepository) {
    suspend operator fun invoke(tagId: Long) {
        return repository.deleteTag(tagId)
    }
}