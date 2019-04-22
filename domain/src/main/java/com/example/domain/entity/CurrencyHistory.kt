package com.example.domain.entity

/**
 * Created by jsmirabal on 4/14/2019.
 */
data class CurrencyHistory(
    val base: String,
    val rates: List<Rate>,
    val startDate: String,
    val endDate: String
) {
    data class Rate(
        val date: String,
        val currencies: List<Currency>
    ) {
        data class Currency(
            val name: String,
            val value: Double
        )
    }
}