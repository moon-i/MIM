package com.moon.morningismiracle.di

import java.util.*

object DateInfo {
    var today = Date()
      get() {
          return clearCalendar().time
      }

    val tomorrow by lazy {
        val cal = clearCalendar()
        cal.add(Calendar.DAY_OF_YEAR, 1)
        cal.time
    }

    var weekStartDate = Date()
        get() {
            val cal = clearCalendar()
            cal[Calendar.DAY_OF_WEEK] = cal.firstDayOfWeek
            return cal.time
        }

    var weekEndDate = Date()
        get() {
            val cal = clearCalendar()
            cal[Calendar.DAY_OF_WEEK] = cal.firstDayOfWeek
            cal.add(Calendar.DAY_OF_YEAR, 6)

            return cal.time
        }

    var monthStartDate = Date()
        get() {
            val cal = clearCalendar()
            cal[Calendar.DAY_OF_MONTH] = 1

            return cal.time
        }

    var monthEndDate = Date()
        get() {
            val cal = clearCalendar()

            // get start of the next month
            cal[Calendar.DAY_OF_MONTH] = 1
            cal.add(Calendar.MONTH, 1)
            cal.add(Calendar.DAY_OF_YEAR, -1)

            return cal.time
        }

    fun clearCalendar(): Calendar {
        val cal = Calendar.getInstance()
        cal[Calendar.HOUR_OF_DAY] = 0
        cal.timeZone = TimeZone.getDefault()
        cal.clear(Calendar.MINUTE)
        cal.clear(Calendar.SECOND)
        cal.clear(Calendar.MILLISECOND)
        return cal
    }
}