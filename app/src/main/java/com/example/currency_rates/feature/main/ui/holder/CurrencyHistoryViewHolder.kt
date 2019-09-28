package com.example.currency_rates.feature.main.ui.holder

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.example.currency_rates.R
import com.example.currency_rates.core.ui.BaseViewHolder
import com.example.currency_rates.core.ui.custom.MyCalendarDialog
import com.example.currency_rates.feature.main.model.CurrencyModel
import com.example.domain.entity.CurrencyHistory
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter


class CurrencyHistoryViewHolder(private val view: View) : BaseViewHolder<CurrencyModel.History>(view) {

    override fun bind(data: CurrencyModel.History) {
        val text = view.findViewById<TextView>(R.id.textView)
        text.setOnClickListener { MyCalendarDialog(itemView.context, onDateSelected()).showCalendar() }

        val barChart = itemView.findViewById<BarChart>(R.id.bar_chart)
        val listData1 = listOf(
            BarEntry(0F, 3.123F),
            BarEntry(1F, 3.453F),
            BarEntry(2F, 2.942F),
            BarEntry(3F, 3.824F),
            BarEntry(4F, 3.142F),
            BarEntry(5F, 3.231F),
            BarEntry(6F, 2.543F)
        )
        val listData2 = listOf(
            BarEntry(0F, 2.123F),
            BarEntry(1F, 2.453F),
            BarEntry(2F, 3.942F),
            BarEntry(3F, 2.824F),
            BarEntry(4F, 2.142F),
            BarEntry(5F, 2.231F),
            BarEntry(6F, 3.543F)
        )
        val dates = listOf(
            "2008",
            "2009",
            "2010",
            "2011",
            "2012",
            "2013",
            "2014"
        )

        val dataSet1 = BarDataSet(listData1, "USD")
        val dataSet2 = BarDataSet(listData2, "GBP")
        dataSet1.color = Color.rgb(104, 241, 175)
        dataSet2.color = Color.rgb(242, 247, 158)

        barChart.xAxis.apply {
            granularity = 1F
            axisMinimum = 0F
            axisMaximum = 7F
            setCenterAxisLabels(true)
            setDrawAxisLine(false)
            gridColor = Color.rgb(0, 0, 0)
            valueFormatter = getValueFormatterFor(dates)
        }

        barChart.axisLeft.apply {
            valueFormatter = LargeValueFormatter()
            setDrawGridLines(false)
            spaceTop = 35f
            axisMinimum = 0f
        }

        barChart.axisRight.apply {
            setDrawGridLines(false)
            isEnabled = false
        }


        barChart.data = BarData(dataSet1, dataSet2).apply { barWidth = 0.3F }
        barChart.setFitBars(true)
        barChart.setPinchZoom(false)
        barChart.setDrawBarShadow(false)
        barChart.setDrawGridBackground(false)
        barChart.description.isEnabled = false
        barChart.animateY(2000)
        barChart.groupBars(0F, 0.35F, 0.03F)
    }

    private fun getValueFormatterFor(dates: List<String>) =
        object : ValueFormatter() {

            override fun getFormattedValue(value: Float) =
                value.toInt().takeIf { it >= 0 && it < dates.size}?.run { dates[this] } ?: ""
        }

    private fun onDateSelected() = object : MyCalendarDialog.CalendarListener {
        override fun onCalendarDateSelected(selectedDate: Long) {
            //TODO add missing logic
        }
    }
}