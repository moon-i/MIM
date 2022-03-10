package com.moon.morningismiracle.plan

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.moon.domain.model.PlanModel
import com.moon.domain.model.PlanState
import com.moon.domain.model.TagModel
import com.moon.morningismiracle.BaseFragment
import com.moon.morningismiracle.R
import com.moon.morningismiracle.custom_view.calendar.BackgroundDecorator
import com.moon.morningismiracle.custom_view.calendar.PlanDecorator
import com.moon.morningismiracle.custom_view.calendar.SaturdayDecorator
import com.moon.morningismiracle.custom_view.calendar.SunDayDecorator
import com.moon.morningismiracle.databinding.FragmentPlanBinding
import com.moon.morningismiracle.di.DateInfo
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*

class PlanFragment : BaseFragment<FragmentPlanBinding>() {
    override var layoutResourceId: Int = R.layout.fragment_plan

    private val planAdapter by lazy { PlanAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initPlanRecyclerView()
    }

    private fun initView() {
        binding.calendarView.apply {
            topbarVisible = false
            setCurrentDate(DateInfo.today)

            binding.dateTextView.text =
                "${CalendarDay.today().year}년 ${CalendarDay.today().month + 1}월"

            // TODO 실제데이터로 변경하기
            val mydate = CalendarDay.from(2022, 2, 16)
            val mydate2 = CalendarDay.from(2022, 2, 17)// year, month, date
            val threeColors = intArrayOf(
                Color.rgb(0, 0, 255),
                Color.rgb(0, 255, 0),
                Color.rgb(255, 0, 0),
                Color.rgb(0, 0, 255),
                Color.rgb(0, 255, 0),
                Color.rgb(255, 0, 0),
                Color.rgb(0, 0, 255),
                Color.rgb(0, 255, 0),
                Color.rgb(255, 0, 0)
            )
            addDecorator(PlanDecorator(hashSetOf(mydate, mydate2), threeColors))
            addDecorator(SaturdayDecorator())
            addDecorator(SunDayDecorator())
            addDecorator(BackgroundDecorator(requireActivity()))
            selectedDate = CalendarDay.today()

            setOnMonthChangedListener { widget, date ->
                date?.let { date ->
                    binding.dateTextView.text = "${date.year}년 ${date.month + 1}월"
                }
            }

            setOnDateChangedListener { widget, date, selected ->
                // TODO open bottom sheet
                val addPlanThatDayBS = AddPlanBottomSheetDialogFragment.newInstance(date)
                addPlanThatDayBS.show(requireActivity().supportFragmentManager, AddPlanBottomSheetDialogFragment.TAG)
            }

        }
        binding.calendarLeftBtn.setOnClickListener {
            binding.calendarView.goToPrevious()
        }
        binding.calendarRightBtn.setOnClickListener {
            binding.calendarView.goToNext()
        }
    }

    fun initPlanRecyclerView() {
        binding.calendarTabPlanList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = planAdapter
        }

        planAdapter.setData(
            listOf(
                PlanModel(0, "과외", PlanState.SUCCESS, Date(), TagModel(0, "과외", "#DC5D6A")),
                PlanModel(0, "과외", PlanState.SUCCESS, Date(), null),
                PlanModel(0, "과외", PlanState.CANCEL, Date(), TagModel(0, "과외", "#E68765")),
                PlanModel(0, "과외", PlanState.WAITING, Date(), TagModel(0, "과외", "#DC5D6A")),
                PlanModel(0, "과외", PlanState.SUCCESS, Date(), TagModel(0, "과외", "#DC5D6A")),
                PlanModel(0, "과외", PlanState.WAITING, Date(), TagModel(0, "과외", "#DC5D6A")),
            )
        )
    }
}