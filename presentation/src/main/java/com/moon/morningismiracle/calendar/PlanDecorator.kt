package com.moon.morningismiracle.calendar

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.HashSet
import android.graphics.Paint
import android.graphics.Canvas
import android.text.style.LineBackgroundSpan

class PlanDecorator(private val dates: HashSet<CalendarDay>, private val colors: IntArray) :
    DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(CustmMultipleDotSpan(5f, colors))
    }
}

class CustmMultipleDotSpan(private val radius: Float, private var color: IntArray) :
    LineBackgroundSpan {
    override fun drawBackground(
        canvas: Canvas, paint: Paint,
        left: Int, right: Int, top: Int, baseline: Int, bottom: Int,
        charSequence: CharSequence,
        start: Int, end: Int, lineNum: Int
    ) {
        val lines =
            if (color.size / MAX_COUNT > MAX_LENGTH) MAX_LENGTH else color.size / MAX_COUNT + if (color.size % MAX_COUNT != 0) 1 else 0

        for (line in 0 until lines) {
            val total = if (line != lines - 1) MAX_COUNT else color.size % MAX_COUNT
            var leftMost = (total - 1) * -10
            for (i in 0 until total) {
                val oldColor = paint.color
                if (color[i] != 0) {
                    paint.color = color[i]
                }
                canvas.drawCircle(
                    ((left + right) / 2 - leftMost).toFloat(),
                    bottom + (15 * line) + 10 + radius,
                    radius,
                    paint
                )
                paint.color = oldColor
                leftMost += 20
            }
        }
    }

    companion object {
        private const val MAX_COUNT = 4
        private const val MAX_LENGTH = 3
    }
}