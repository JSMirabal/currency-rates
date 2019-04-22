package com.example.domain.repository

import com.example.data.Provider
import com.example.data.core.Either
import com.example.data.network.Service.Params
import com.example.domain.core.toCurrencyHistory

/**
 * Created by jsmirabal on 4/19/2019.
 */
class CurrencyRepository {

    fun fetchHistory(params: Params) =
        Provider.fetchHistory(params).transform(
            {
                Either.Left(it)
            },
            {
                Either.Right(it.toCurrencyHistory())
            }
        )
}