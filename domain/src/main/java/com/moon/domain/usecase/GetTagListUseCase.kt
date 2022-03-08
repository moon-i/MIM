package com.moon.domain.usecase

import com.moon.domain.model.TagModel
import com.moon.domain.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTagListUseCase @Inject constructor(private val repository: TagRepository) {
    operator fun invoke(): Flow<List<TagModel>> {
        return repository.getTagList()
    }
}