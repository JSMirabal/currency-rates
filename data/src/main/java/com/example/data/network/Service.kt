package com.example.data.network

import com.example.domain.core.Either
import com.example.domain.core.Either.Left
import com.example.domain.core.Either.Right
import com.example.domain.core.Failure
import com.example.domain.core.Params
import javax.inject.Inject

/**
 * Created by jsmirabal on 4/21/2019.
 */
class Service @Inject constructor(private val currencyApi: CurrencyApi) {

    suspend fun fetchHistory(params: Params): Either<Failure, HistoryResponse> =
        try {
            Right(
                currencyApi.fetchHistory(
                    params.startDate,
                    params.endDate,
                    params.base,
                    params.symbols
                )
            )
        } catch (e: Exception) {
            Left(Failure.ApiFailure("Unexpected error: ${e.message}"))
        }
}