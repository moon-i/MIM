package com.moon.morningismiracle.custom_view.calendar

import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.CalendarDay
import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.moon.morningismiracle.R

class BackgroundDecorator(
    context: Activity,
) : DayViewDecorator {
    private var drawable: Drawable? = null

    init {
        drawable = ContextCompat.getDrawable(context, R.drawable.calendar_background_selector)
    }

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return true
    }

    override fun decorate(view: DayViewFacade) {
        drawable?.let {
            view.setSelectionDrawable(it)
        }
    }
}