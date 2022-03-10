package com.moon.morningismiracle.plan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moon.domain.model.PlanModel
import com.moon.domain.model.PlanState
import com.moon.domain.usecase.*
import com.moon.morningismiracle.di.DateInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
    private val getPlanListUseCase: GetPlanListUseCase,
    private val setPlanStateUseCase: SetPlanStateUseCase,
    private val setPlanDelayOneDayUseCase: SetPlanDelayOneDayUseCase,
) : ViewModel() {

    private var _planDataList = MutableStateFlow<List<PlanModel>>(emptyList())
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

    fun getPlanList(date: Date) {
        CoroutineScope(Dispatchers.IO).launch {
            getPlanListUseCase(date = date).collect { list ->
                _planDataList.value = list
            }
        }
    }

    fun setPlan(planId: Long, state: PlanState) {
        viewModelScope.launch {
            setPlanStateUseCase(planId, state)
            getPlanList(DateInfo.today)
        }
    }

    fun setPlanDelayOndDay(planId: Long, newDate: Date) {
        viewModelScope.launch {
            setPlanDelayOneDayUseCase(planId, newDate)
            getPlanList(DateInfo.today)
        }
    }
}