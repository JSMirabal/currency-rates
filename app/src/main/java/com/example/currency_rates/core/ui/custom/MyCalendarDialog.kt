package com.example.currency_rates.core.ui.custom

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.CalendarView
import android.widget.TextView
import com.example.currency_rates.R
import com.example.currency_rates.core.extensions.findRootView
import com.example.currency_rates.core.extensions.inflate
import com.example.currency_rates.core.util.DATE_FORMAT_CALENDAR_DATE
import com.example.currency_rates.core.util.DATE_FORMAT_CALENDAR_DAY_NAME
import com.example.currency_rates.core.util.getDateFrom
import com.example.currency_rates.core.util.getFormattedDateFrom

class MyCalendarDialog(private val context: Context?, private val listener: CalendarListener) {

    private val calendarContainer by lazy { context?.inflate(R.layout.custom_calendar, context.findRootView()) }
    private val calendarView by lazy { calendarContainer?.findViewById<CalendarView>(R.id.calendarView) }
    private val calendarDayName by lazy { calendarContainer?.findViewById<TextView>(R.id.calendarDayName) }
    private val calendarLongDate by lazy { calendarContainer?.findViewById<TextView>(R.id.calendarLongDate) }

    fun showCalendar(fromDate: Long = 0L) {
        calendarContainer ?: return
        setupCalendar(fromDate)

        AlertDialog.Builder(context)
            .setPositiveButton(context?.getString(R.string.ok), onPositiveButtonClicked())
            .setNegativeButton(context?.getString(R.string.cancel)) { d, _ -> d.dismiss()}
            .setView(calendarContainer)
            .create()
            .show()
    }

    private fun onPositiveButtonClicked() = DialogInterface.OnClickListener { dialog, _ ->
        listener.onCalendarDateSelected(calendarView?.date ?: 0L)
        dialog.dismiss()
    }

    private fun setupCalendar(fromDate: Long) {
        calendarView?.setOnDateChangeListener(onDateChange())

        if (fromDate > 0L) calendarView?.date = fromDate

        updateCalendar(calendarView?.date)
    }

    private fun onDateChange() = CalendarView.OnDateChangeListener { _, year, month, day ->
        val date = getDateFrom(year, month, day)
        updateCalendar(date)
    }

    private fun updateCalendar(date: Long?) {
        calendarDayName?.text = getFormattedDateFrom(date, DATE_FORMAT_CALENDAR_DAY_NAME)
        calendarLongDate?.text = getFormattedDateFrom(date, DATE_FORMAT_CALENDAR_DATE)
    }

    interface CalendarListener {
        fun onCalendarDateSelected(selectedDate: Long)
    }
}