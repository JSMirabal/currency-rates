package com.example.domain.repository

import com.example.data.core.Either
import com.example.data.core.Failure
import com.example.domain.entity.CurrencyHistory
import kotlinx.coroutines.coroutineScope

/**
 * Created by jsmirabal on 4/19/2019.
 */
class CurrencyRepository {

    data class Range(val startDate: String, val endDate: String)

    suspend fun fetchHistory(params: Range): Either<Failure, CurrencyHistory> {
        // TODO: Change with actual implementation in data module
        return coroutineScope { Either.Right(CurrencyHistory("EUR", emptyList(), params.startDate, params.endDate)) }
    }
}