package com.moon.morningismiracle.home

import androidx.lifecycle.ViewModel
import com.moon.domain.model.PlanModel
import com.moon.domain.model.PlanState
import com.moon.domain.usecase.GetPlanListUseCase
import com.moon.domain.usecase.SetPlanDelayOneDayUseCase
import com.moon.domain.usecase.SetPlanStateBeforeTodayUseCase
import com.moon.domain.usecase.SetPlanStateUseCase
import com.moon.morningismiracle.DataStore
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
class HomeViewModel @Inject constructor(
    private val getPlanListUseCase: GetPlanListUseCase,
    private val setPlanStateUseCase: SetPlanStateUseCase,
    private val setPlanDelayOneDayUseCase: SetPlanDelayOneDayUseCase,
    private val setPlanStateBeforeTodayUseCase: SetPlanStateBeforeTodayUseCase,
    private val dateStore: DataStore,
): ViewModel() {

    private val _planDataList = MutableStateFlow<List<PlanModel>>(emptyList())
    val planDataList: StateFlow<List<PlanModel>> = _planDataList

    fun getPlanList(date: Date) {
        CoroutineScope(Dispatchers.IO).launch {
            getPlanListUseCase(date = date).collect { list ->
                _planDataList.value = list
            }
        }
    }

    fun setPlan(planId: Long, state: PlanState) {
        CoroutineScope(Dispatchers.IO).launch {
            setPlanStateUseCase(planId, state)
            getPlanList(DateInfo.today)
        }
    }

    fun setPlanDelayOndDay(planId: Long, newDate: Date) {
        CoroutineScope(Dispatchers.IO).launch {
            setPlanDelayOneDayUseCase(planId, newDate)
            getPlanList(DateInfo.today)
        }
    }

    fun setPlanStateBeforeToday(date: Date) {
        CoroutineScope(Dispatchers.IO).launch {
            dateStore.lastDate.collect {
                if (it < date.time) {
                    setPlanStateBeforeTodayUseCase(date)
                    dateStore.setLastDate(date.time)
                }
            }
        }
    }
}