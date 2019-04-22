package com.example.data

import com.example.data.network.Service

/**
 * Created by jsmirabal on 4/21/2019.
 */
object Provider {

    fun fetchHistory(
        startDate: String,
        endDate: String,
        base: String = "EUR",
        symbols: String = "USD"
    ) =
        Service.fetchHistory(
            Service.Params(startDate, endDate, base, symbols)
        )
}