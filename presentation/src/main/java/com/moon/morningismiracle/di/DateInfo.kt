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
            // get start of this week in milliseconds
            cal[Calendar.DAY_OF_WEEK] = cal.firstDayOfWeek
            System.out.println("Start of this week:       " + cal.time)

            return cal.time
        }

    var weekEndDate = Date()
        get() {
            val cal = clearCalendar()

            // start of the next week문
            cal[Calendar.DAY_OF_WEEK] = cal.firstDayOfWeek
            cal.add(Calendar.WEEK_OF_YEAR, 1)
            System.out.println("Start of the next week:   " + cal.time)

            return cal.time
        }

    var monthStartDate = Date()
        get() {
            val cal = clearCalendar()
            // get start of the month
            cal[Calendar.DAY_OF_MONTH] = 1
            System.out.println("Start of the month:       " + cal.time)
            System.out.println("... in milliseconds:      " + cal.timeInMillis)

            return cal.time
        }

    var monthEndDate = Date()
        get() {
            val cal = clearCalendar()

            // get start of the next month
            cal[Calendar.DAY_OF_MONTH] = 1
            cal.add(Calendar.MONTH, 1)
            System.out.println("Start of the next month:  " + cal.time)

            return cal.time
        }

    fun clearCalendar(): Calendar {
        val cal = Calendar.getInstance()
        cal[Calendar.HOUR_OF_DAY] = 0 // ! clear would not reset the hour of day !
        cal.timeZone = TimeZone.getDefault()
        cal.clear(Calendar.MINUTE)
        cal.clear(Calendar.SECOND)
        cal.clear(Calendar.MILLISECOND)
        return cal
    }
}