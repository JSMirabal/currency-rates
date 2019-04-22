package com.example.domain.repository

import com.example.domain.core.Either
import com.example.domain.core.Failure
import com.example.domain.entity.CurrencyRate
import kotlinx.coroutines.coroutineScope

/**
 * Created by jsmirabal on 4/19/2019.
 */
class CurrencyRepository {

    data class Range(val startDate: String, val endDate: String)

    suspend fun fetchRange(params: Range): Either<Failure, CurrencyRate> {
        // TODO: Change with actual implementation in data module
        return coroutineScope { Either.Right(CurrencyRate("EUR", emptyList(), params.startDate, params.endDate)) }
    }
}