package com.moon.morningismiracle.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moon.domain.model.PlanModel
import com.moon.domain.usecase.GetPlanListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPlanListUseCase: GetPlanListUseCase
): ViewModel() {

    private val _planDataList = MutableStateFlow<List<PlanModel>>(emptyList())
    val planDataList: StateFlow<List<PlanModel>> = _planDataList

    fun getPlanList(date: Date) {
        viewModelScope.launch {
            getPlanListUseCase(date = date).collect { list ->
                _planDataList.value = list
            }
        }
    }
}