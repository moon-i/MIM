package com.moon.domain.usecase

import com.moon.domain.model.TagModel
import com.moon.domain.repository.TagRepository
import javax.inject.Inject

class GetTagListUseCase @Inject constructor(private val repository: TagRepository) {
    suspend operator fun invoke(): List<TagModel> {
        return repository.getTagList()
    }
}