package com.example.data.core

import com.example.data.network.HistoryResponse
import com.example.domain.entity.CurrencyHistory

fun HistoryResponse.toCurrencyHistory() =
    CurrencyHistory(
        this.base,
        convertRates(this.rates),
        this.startDate,
        this.endDate
    )

private fun convertRates(rates: List<HistoryResponse.Rate>) =
    rates.map { rate ->
        CurrencyHistory.Rate(
            rate.date,
            rate.currencies.map { currency ->
                CurrencyHistory.Rate.Currency(
                    currency.name,
                    currency.value
                )
            }
        )
    }
