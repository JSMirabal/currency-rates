package com.example.currency_rates.core.util

import android.util.Log
import com.example.currency_rates.BuildConfig
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date
import java.util.Calendar


fun debug(block: () -> Unit) {
    if (BuildConfig.DEBUG) block()
}

fun getFormattedDateFrom(date: Long?, format: String): String = try {
    date ?: throw IllegalArgumentException("[date] parameter must not be null")
    SimpleDateFormat(format, Locale.getDefault()).format(Date(date))
} catch (e: Exception) {
    Log.e(ERROR_TAG, "Couldn't parse date $date. Error: ${e.message} Method: ${Thread.currentThread().stackTrace[0]}")
    "Unknown"
}

fun getDateFrom(year: Int, month: Int, day: Int) =
    Calendar.getInstance().apply {
        set(year, month, day)
    }.timeInMillis
