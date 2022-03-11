package com.moon.morningismiracle.plan

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.moon.domain.model.PlanModel
import com.moon.morningismiracle.BaseFragment
import com.moon.morningismiracle.R
import com.moon.morningismiracle.custom_view.calendar.BackgroundDecorator
import com.moon.morningismiracle.custom_view.calendar.PlanDecorator
import com.moon.morningismiracle.custom_view.calendar.SaturdayDecorator
import com.moon.morningismiracle.custom_view.calendar.SunDayDecorator
import com.moon.morningismiracle.databinding.FragmentPlanBinding
import com.moon.morningismiracle.di.DateInfo
import com.prolificinteractive.materialcalendarview.CalendarDay
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PlanFragment : BaseFragment<FragmentPlanBinding>() {
    override var layoutResourceId: Int = R.layout.fragment_plan

    private val planViewModel: PlanViewModel by viewModels()
    private val planAdapter by lazy { PlanAdapter() }

    private var selectedDateForAddPlan = CalendarDay.from(DateInfo.today)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(DateInfo.today)
        initPlanRecyclerView(DateInfo.today)
        setObserve()
        setOnClick()
    }

    private fun initView(date: Date) {
        setPlanTitle(CalendarDay.from(date))
        binding.calendarView.apply {
            topbarVisible = false
            selectedDate = CalendarDay.today()

            setCurrentDate(date)

            addDecorator(SaturdayDecorator())
            addDecorator(SunDayDecorator())
            addDecorator(BackgroundDecorator(requireActivity()))

            setOnMonthChangedListener { widget, date ->
                date?.let { date ->
                    setPlanTitle(date)
                }
            }

            setOnDateChangedListener { widget, date, selected ->
                binding.nestedScrollView.smoothScrollTo(0, binding.nestedScrollView.getChildAt(0).height)
                planViewModel.getSelectDayPlanList(date.date)
                selectedDateForAddPlan = date
                setBottomListTitle(date)
            }
        }
    }

    private fun initPlanRecyclerView(date: Date) {
        binding.calendarTabPlanList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = planAdapter
            planAdapter.onDeleteBtn = ::onDeleteBtn
        }
        planViewModel.getSelectDayPlanList(date)
    }

    private fun setObserve() {
        planViewModel.selectDayPlanDataList.asLiveData().observe(this) { list ->
            if(list.isEmpty()) {
                binding.calendarTabPlanList.visibility = View.GONE
                binding.noListImageView.visibility = View.VISIBLE
                binding.noListTextView.visibility = View.VISIBLE
            } else {
                planAdapter.setData(list)
                binding.calendarTabPlanList.visibility = View.VISIBLE
                binding.noListImageView.visibility = View.GONE
                binding.noListTextView.visibility = View.GONE
            }
        }
    }

    private fun setOnClick() {
        binding.calendarLeftBtn.setOnClickListener {
            binding.calendarView.goToPrevious()
        }
        binding.calendarRightBtn.setOnClickListener {
            binding.calendarView.goToNext()
        }
        binding.addPlanBtn.setOnClickListener {
            showAddBottomSheet()
        }
    }

    private fun setBottomListTitle(date: CalendarDay) {
        binding.planListThatDayTitle.text = "${date.year}년 ${date.month+1}월 ${date.day}일 계획"
    }

    private fun setPlanTitle(date: CalendarDay) {
        binding.dateTextView.text = "${date.year}년 ${date.month + 1}월"
    }

    private fun showAddBottomSheet() {
        val addPlanThatDayBS = AddPlanBottomSheetDialogFragment.newInstance(selectedDateForAddPlan)
        addPlanThatDayBS.show(
            requireActivity().supportFragmentManager,
            AddPlanBottomSheetDialogFragment.TAG
        )
        requireActivity().supportFragmentManager.executePendingTransactions()
        addPlanThatDayBS.dialog?.setOnDismissListener {
            planViewModel.getSelectDayPlanList(selectedDateForAddPlan.date)
        }
    }

    private fun onDeleteBtn(item: PlanModel) {
        planViewModel.deletePlan(item, true)
    }
}