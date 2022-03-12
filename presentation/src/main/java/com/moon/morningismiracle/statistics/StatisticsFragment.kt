package com.moon.morningismiracle.statistics

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.moon.morningismiracle.BaseFragment
import com.moon.morningismiracle.R
import com.moon.morningismiracle.databinding.FragmentStatisticsBinding
import com.moon.morningismiracle.di.DateInfo
import com.moon.morningismiracle.setDrawableTint
import com.prolificinteractive.materialcalendarview.CalendarDay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment : BaseFragment<FragmentStatisticsBinding>() {
    override var layoutResourceId: Int = R.layout.fragment_statistics

    private val statisticsViewModel: StatisticsViewModel by viewModels()
    private val tagWeekStatisticsAdapter by lazy { TagStatisticsAdapter() }
    private val tagMonthStatisticsAdapter by lazy { TagStatisticsAdapter() }

    val weekStartDate = CalendarDay.from(DateInfo.weekStartDate)
    val weekEndDate =  CalendarDay.from(DateInfo.weekEndDate)

    val monthStartDate = CalendarDay.from(DateInfo.monthStartDate)
    val monthEndDate = CalendarDay.from(DateInfo.monthEndDate)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getData()
        setObserver()
    }

    private fun initView() {
        binding.tagStatisticsWeekRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = tagWeekStatisticsAdapter
            isNestedScrollingEnabled = false
            itemAnimator = null
        }

        binding.tagStatisticsMonthRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = tagMonthStatisticsAdapter
            isNestedScrollingEnabled = false
            itemAnimator = null
        }

        binding.totalWeekInfo.apply {
            infoLayoutTitle.text = getString(R.string.statisticsTotal)
            infoLayoutLogoImageView.setBackgroundResource(R.drawable.ic_all)
        }

        binding.failWeekInfo.apply {
            infoLayoutTitle.text = getString(R.string.statisticsFail)
            infoLayoutLogoImageView.setBackgroundResource(R.drawable.ic_fail)
        }

        binding.successWeekInfo.apply {
            infoLayoutTitle.text = getString(R.string.statisticsSuccess)
            infoLayoutLogoImageView.setBackgroundResource(R.drawable.ic_success)
        }

        binding.totalMonthInfo.apply {
            infoLayoutTitle.text = getString(R.string.statisticsTotal)
            infoLayoutLogoImageView.setBackgroundResource(R.drawable.ic_all)
        }

        binding.failMonthInfo.apply {
            infoLayoutTitle.text = getString(R.string.statisticsFail)
            infoLayoutLogoImageView.setBackgroundResource(R.drawable.ic_fail)
        }

        binding.successMonthInfo.apply {
            infoLayoutTitle.text = getString(R.string.statisticsSuccess)
            infoLayoutLogoImageView.setBackgroundResource(R.drawable.ic_success)
        }

        binding.thisWeekDate.text = String.format(
            getString(R.string.statisticsDateRange),
            weekStartDate.year,
            weekStartDate.month + 1,
            weekStartDate.day,
            weekEndDate.year,
            weekEndDate.month + 1,
            weekEndDate.day,
        )

        binding.thisMonthDate.text = String.format(
            getString(R.string.statisticsDateRange),
            monthStartDate.year,
            monthStartDate.month + 1,
            monthStartDate.day,
            monthEndDate.year,
            monthEndDate.month + 1,
            monthEndDate.day,
        )
    }

    private fun getData() {
        statisticsViewModel.getResultStateCountForWeek(DateInfo.weekStartDate, DateInfo.weekEndDate)
        statisticsViewModel.getResultStateCountForMonth(DateInfo.monthStartDate, DateInfo.monthEndDate)
        statisticsViewModel.getResultTagCountForWeek(DateInfo.weekStartDate, DateInfo.weekEndDate)
        statisticsViewModel.getResultTagCountForMonth(DateInfo.monthStartDate, DateInfo.monthEndDate)
    }

    private fun setObserver() {
        statisticsViewModel.weekStateCount.asLiveData().observe(this) { model ->
            val result = getPercent(model.successCount.toFloat(), model.totalCont.toFloat())
            setResultView(result, binding.medalWeekImageView, binding.statisticsWeekMyDescription)
            binding.statisticsWeekResult.text = "${result}%"
            binding.totalWeekInfo.infoLayoutValue.text = String.format(getString(R.string.statisticsUnit), model.totalCont)
            binding.successWeekInfo.infoLayoutValue.text = String.format(getString(R.string.statisticsUnit), model.successCount)
            binding.failWeekInfo.infoLayoutValue.text = String.format(getString(R.string.statisticsUnit), model.failCount)
            binding.cancelWeekInfo.infoLayoutValue.text = String.format(getString(R.string.statisticsUnit), model.cancelCount)
        }

        statisticsViewModel.monthStateCount.asLiveData().observe(this) { model ->
            val result = getPercent(model.successCount.toFloat(), model.totalCont.toFloat())
            setResultView(result, binding.medalMonthImageView, binding.statisticsMonthMyDescription)
            binding.statisticsMonthResult.text = "${result}%"
            binding.totalMonthInfo.infoLayoutValue.text = String.format(getString(R.string.statisticsUnit), model.totalCont)
            binding.successMonthInfo.infoLayoutValue.text = String.format(getString(R.string.statisticsUnit), model.successCount)
            binding.failMonthInfo.infoLayoutValue.text = String.format(getString(R.string.statisticsUnit), model.failCount)
            binding.cancelMonthInfo.infoLayoutValue.text = String.format(getString(R.string.statisticsUnit), model.cancelCount)
        }

        statisticsViewModel.tagStatisticsWeekList.asLiveData().observe(this) { list ->
            tagWeekStatisticsAdapter.setData(list)
        }

        statisticsViewModel.tagStatisticsMonthList.asLiveData().observe(this) { list ->
            tagMonthStatisticsAdapter.setData(list)
        }
    }

    private fun getPercent(success: Float, total: Float): Int {
        return if (total != 0f) {
            ((success / total) * 100).toInt()
        } else {
            0
        }
    }

    private fun setResultView(result: Int, medalImageView: ImageView, resultTextView: TextView) {
        when (result) {
            in 80 .. 100 -> {
                resultTextView.text = getString(R.string.myDescription100)
                medalImageView.background.setDrawableTint(R.color.main_3, requireContext())
            }
            in 60 .. 79 -> {
                resultTextView.text = getString(R.string.myDescription80)
                medalImageView.background.setDrawableTint(R.color.main_4, requireContext())
            }
            in 40 .. 59 -> {
                resultTextView.text = getString(R.string.myDescription60)
                medalImageView.background.setDrawableTint(R.color.main_5, requireContext())
            }
            in 20 .. 39 -> {
                resultTextView.text = getString(R.string.myDescription40)
                medalImageView.background.setDrawableTint(R.color.main_6, requireContext())
            }
            in 0 .. 19 -> {
                resultTextView.text = getString(R.string.myDescription20)
                medalImageView.background.setDrawableTint(R.color.main_7, requireContext())
            }
        }
    }
}