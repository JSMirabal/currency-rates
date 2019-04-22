package com.example.domain.usecase

import com.example.data.core.Either
import com.example.data.core.Failure
import com.example.data.network.Service.Params
import com.example.domain.core.BaseUseCase
import com.example.domain.entity.CurrencyHistory
import com.example.domain.repository.CurrencyRepository

/**
 * Created by jsmirabal on 4/19/2019.
 */
class FetchCurrencyHistory(private val repository: CurrencyRepository): BaseUseCase<CurrencyHistory, Params>() {

    override suspend fun run(params: Params): Either<Failure, CurrencyHistory> = repository.fetchHistory(params)
}