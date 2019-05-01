package com.example.data.core

import com.example.data.db.dao.HistoryCache
import com.example.data.db.dao.RateWithCurrencies
import com.example.data.network.HistoryResponse
import com.example.domain.entity.CurrencyHistory

fun HistoryResponse.toCurrencyHistory() =
    CurrencyHistory(
        this.base,
        convertRatesFromService(this.rates),
        this.startDate,
        this.endDate
    )

fun HistoryCache.toCurrencyHistory() =
    CurrencyHistory(
        this.history?.base ?: NOT_FOUND,
        convertRatesFromCache(this.rates),
        this.history?.startDate ?: NOT_FOUND,
        this.history?.endDate ?: NOT_FOUND
    )

private fun convertRatesFromCache(rates: List<RateWithCurrencies>?) =
    rates?.map {
        CurrencyHistory.Rate(
            it.rate?.date ?: NOT_FOUND,
            it.currencies?.map { currency ->
                CurrencyHistory.Rate.Currency(
                    currency.name,
                    currency.value
                )
            }.orEmpty()
        )
    }.orEmpty()

private fun convertRatesFromService(rates: List<HistoryResponse.Rate>) =
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
