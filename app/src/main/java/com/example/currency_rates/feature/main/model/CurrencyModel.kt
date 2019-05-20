package com.example.currency_rates.feature.main.model

import com.example.domain.entity.CurrencyHistory

data class CurrencyModel(
    val rates: List<CurrencyHistory.Rate>
) {
    var type = 0
}
