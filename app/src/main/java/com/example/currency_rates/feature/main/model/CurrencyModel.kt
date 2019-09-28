package com.example.currency_rates.feature.main.model

import com.example.currency_rates.feature.main.ui.MainViewType
import com.example.domain.entity.CurrencyHistory

sealed class CurrencyModel() {

    data class History(val currencyHistory: CurrencyHistory? = null) : CurrencyModel()
    data class Average(val rates: List<CurrencyHistory> = emptyList()) : CurrencyModel()
    data class Latest(val rates: List<CurrencyHistory> = emptyList()) : CurrencyModel()
}
