package com.moon.morningismiracle.plan

import androidx.lifecycle.ViewModel
import com.moon.domain.model.PlanModel
import com.moon.domain.usecase.*
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
    private val getPlanListUseCase: GetPlanListUseCase,
) : ViewModel() {

    private var _selectDayPlanDataList = MutableStateFlow<List<PlanModel>>(emptyList())
    val selectDayPlanDataList: StateFlow<List<PlanModel>> = _selectDayPlanDataList

    fun addPlan(planData: PlanModel) {
        CoroutineScope(Dispatchers.IO).launch {
            addPlanUseCase(planData)
            getSelectDayPlanList(planData.planDate)
        }
    }

    fun deletePlan(planData: PlanModel, isSelectedDate: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            deletePlanUseCase(planData)
            getSelectDayPlanList(planData.planDate)
        }
    }

    fun getSelectDayPlanList(date: Date) {
        CoroutineScope(Dispatchers.IO).launch {
            _selectDayPlanDataList.value = getPlanListUseCase(date = date)
        }
    }
}