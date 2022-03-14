package com.moon.morningismiracle.statistics

import androidx.lifecycle.ViewModel
import com.moon.domain.model.StatisticsTotalModel
import com.moon.domain.model.TagStatisticsModel
import com.moon.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val getResultStateCountUseCase: GetResultStateCountUseCase,
    private val getResultTagCountUseCase: GetResultTagCountUseCase,
) : ViewModel() {

    private val _weekStateCount =
        MutableStateFlow<StatisticsTotalModel>(StatisticsTotalModel(0, 0, 0, 0))
    val weekStateCount: StateFlow<StatisticsTotalModel> = _weekStateCount

    private val _monthStateCount =
        MutableStateFlow<StatisticsTotalModel>(StatisticsTotalModel(0, 0, 0, 0))
    val monthStateCount: StateFlow<StatisticsTotalModel> = _monthStateCount

    private val _tagStatisticsWeekList = MutableStateFlow<List<TagStatisticsModel>>(emptyList())
    val tagStatisticsWeekList: StateFlow<List<TagStatisticsModel>> = _tagStatisticsWeekList

    private val _tagStatisticsMonthList = MutableStateFlow<List<TagStatisticsModel>>(emptyList())
    val tagStatisticsMonthList: StateFlow<List<TagStatisticsModel>> = _tagStatisticsMonthList

    fun getResultStateCountForWeek(startDate: Date, endDate: Date) {
        CoroutineScope(Dispatchers.IO).launch {
            _weekStateCount.value = getResultStateCountUseCase(startDate, endDate)
        }
    }

    fun getResultStateCountForMonth(startDate: Date, endDate: Date) {
        CoroutineScope(Dispatchers.IO).launch {
            _monthStateCount.value = getResultStateCountUseCase(startDate, endDate)
        }
    }

    fun getResultTagCountForWeek(startDate: Date, endDate: Date) {
        CoroutineScope(Dispatchers.IO).launch {
            _tagStatisticsWeekList.value = getResultTagCountUseCase(startDate, endDate)
        }
    }

    fun getResultTagCountForMonth(startDate: Date, endDate: Date) {
        CoroutineScope(Dispatchers.IO).launch {
            _tagStatisticsMonthList.value = getResultTagCountUseCase(startDate, endDate)
        }
    }
}