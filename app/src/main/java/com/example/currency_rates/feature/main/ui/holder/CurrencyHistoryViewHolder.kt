package com.example.currency_rates.feature.main.ui.holder

import android.view.View
import android.widget.TextView
import com.example.currency_rates.R
import com.example.currency_rates.core.ui.BaseViewHolder
import com.example.currency_rates.core.ui.custom.MyCalendarDialog

class CurrencyHistoryViewHolder(private val view: View) : BaseViewHolder(view) {

    override fun bind() {
        val text = itemView.findViewById<TextView>(R.id.textView)
        text.setOnClickListener { MyCalendarDialog(itemView.context, onDateSelected()).showCalendar() }
    }

    private fun onDateSelected() = object : MyCalendarDialog.CalendarListener {
        override fun onCalendarDateSelected(selectedDate: Long) {
            //TODO add missing logic
        }
    }
}