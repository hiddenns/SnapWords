package com.khalore.core.ext

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar


//val Calendar.timeToNextDayTimeMillis: Long
//    get(): Long {
//        val now = timeInMillis
//        add(Calendar.DAY_OF_YEAR, 1)
//        set(Calendar.AM_PM, Calendar.AM)
//        set(Calendar.HOUR, 0)
//        set(Calendar.MINUTE, 0)
//        set(Calendar.SECOND, 0)
//        set(Calendar.MILLISECOND, 0)
//        val tomorrow = timeInMillis
//        clear()
//        return tomorrow - now
//    }

val Calendar.timeNextDayMillis: Long
    get(): Long {
        add(Calendar.DAY_OF_YEAR, 1)
        set(Calendar.AM_PM, Calendar.AM)
        set(Calendar.HOUR, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        val tomorrow = timeInMillis
        clear()
        return tomorrow
    }

val Calendar.timeTodayMillis: Long
    get(): Long {
        add(Calendar.DAY_OF_YEAR, 0)
        set(Calendar.AM_PM, Calendar.AM)
        set(Calendar.HOUR, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        val today = timeInMillis
        clear()
        return today
    }


fun Long.getDate(dateFormat: String = "dd/MM/yyyy hh:mm:ss.SSS"): String {
    val formatter = SimpleDateFormat(dateFormat);
    val calendar = Calendar.getInstance();
    calendar.timeInMillis = this;
    return formatter.format(calendar.time);
}