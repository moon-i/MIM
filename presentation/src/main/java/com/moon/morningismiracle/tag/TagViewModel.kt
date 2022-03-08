package com.moon.morningismiracle.tag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moon.domain.model.TagModel
import com.moon.domain.usecase.AddTagUseCase
import com.moon.domain.usecase.DeleteTagUseCase
import com.moon.domain.usecase.GetTagListUseCase
import com.moon.domain.usecase.UpdateTagColorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TagViewModel @Inject constructor(
    private val addTagUseCase: AddTagUseCase,
    private val deleteTagUseCase: DeleteTagUseCase,
    private val updateTagColorUseCase: UpdateTagColorUseCase,
    private val getTagListUseCase: GetTagListUseCase,
): ViewModel() {

    private val _tagDataList = MutableStateFlow<List<TagModel>>(emptyList())
    val tagDataList: StateFlow<List<TagModel>> = _tagDataList

    fun getTagList() {
        viewModelScope.launch {
            getTagListUseCase().collect { dataResult ->
                _tagDataList.value = dataResult
            }
        }
    }

    fun addTag(tagModel: TagModel) {
        viewModelScope.launch {
            addTagUseCase(tagModel)
        }
    }

    fun deleteTag(tagId: Long) {
        viewModelScope.launch {
            deleteTagUseCase(tagId)
        }
    }

    fun updateTagColor(tagId: Long, color: String) {
        viewModelScope.launch {
            updateTagColorUseCase(tagId, color)
        }
    }
}