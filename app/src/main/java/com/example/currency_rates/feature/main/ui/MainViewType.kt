package com.example.currency_rates.feature.main.ui

enum class MainViewType(val numericType: Int) {
    HISTORY(0),
    AVERAGE(1),
    LATEST(2);

    companion object {
        fun getFrom(type: MainViewType) = values().first { it.name == type.name }

        fun getFrom(numericType: Int) = values().first { it.numericType == numericType }
    }
}