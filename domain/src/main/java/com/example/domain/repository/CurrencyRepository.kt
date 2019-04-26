package com.example.domain.repository

import com.example.domain.core.Either
import com.example.domain.core.Failure
import com.example.domain.core.Params
import com.example.domain.entity.CurrencyHistory

/**
 * Created by jsmirabal on 4/19/2019.
 */
interface CurrencyRepository {
    fun fetchHistory(params: Params): Either<Failure, CurrencyHistory>
}