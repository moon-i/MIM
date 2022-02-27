package com.moon.morningismiracle

import android.graphics.Color
import android.os.Bundle
import com.moon.morningismiracle.custom_view.calendar.*
import com.moon.morningismiracle.databinding.ActivityMaterialCalendarViewTestBinding
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.*


class MaterialCalendarViewTestActivity : BaseActivity<ActivityMaterialCalendarViewTestBinding>() {

    override var layoutResourceId: Int = R.layout.activity_material_calendar_view_test

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.calendarView.apply {
            topbarVisible = false
            val calendar = Calendar.getInstance()
            val today: Date = calendar.time

            calendar.add(Calendar.DAY_OF_YEAR, 1)
            val tomorrow = calendar.time
            setCurrentDate(today)

            binding.dateTextView.text =
                "${CalendarDay.today().year}년 ${CalendarDay.today().month + 1}월"
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
            addDecorator(BackgroundDecorator(this@MaterialCalendarViewTestActivity))
            selectedDate = CalendarDay.today()

            setOnMonthChangedListener { widget, date ->
                date?.let { date ->
                    binding.dateTextView.text = "${date.year}년 ${date.month + 1}월"
                }
            }

            setOnDateChangedListener { widget, date, selected ->
                // TODO open bottom sheet
            }

        }
        binding.calendarLeftBtn.setOnClickListener {
            binding.calendarView.goToPrevious()
        }
        binding.calendarRightBtn.setOnClickListener {
            binding.calendarView.goToNext()
        }

    }
}