package com.moon.morningismiracle.plan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moon.domain.model.PlanModel
import com.moon.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PlanViewModel @Inject constructor(
    private val addPlanUseCase: AddPlanUseCase,
    private val deletePlanUseCase: DeletePlanUseCase,
    private val setPlanStateBeforeToday: SetPlanStateBeforeToday,
    private val setPlanDelayOneDayUseCase: SetPlanDelayOneDayUseCase,
    private val getPlanListUseCase: GetPlanListUseCase,
): ViewModel() {

    private val _planDataList = MutableStateFlow<List<PlanModel>>(emptyList())
    val planDataList: StateFlow<List<PlanModel>> = _planDataList

    fun addPlan(planData: PlanModel) {
        viewModelScope.launch {
            addPlanUseCase(planData)
        }
    }

    fun deletePlan(planData: PlanModel) {
        viewModelScope.launch {
            deletePlanUseCase(planData)
        }
    }

    fun setPlanDelayOndDay(planId: Long, newDate: Date) {
        viewModelScope.launch {
            setPlanDelayOneDayUseCase(planId, newDate)
        }
    }

    fun getPlanList(date: Date) {
        viewModelScope.launch {
            getPlanListUseCase(date).collect { list ->
                _planDataList.value = list
            }
        }
    }
}