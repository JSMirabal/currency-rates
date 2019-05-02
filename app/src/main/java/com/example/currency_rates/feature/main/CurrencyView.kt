package com.example.currency_rates.feature.main

import com.example.domain.entity.CurrencyHistory

data class CurrencyView(
    val rates: List<CurrencyHistory.Rate>
)
