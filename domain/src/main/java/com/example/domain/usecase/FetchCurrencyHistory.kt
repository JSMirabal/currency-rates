package com.example.domain.usecase

import com.example.domain.core.BaseUseCase
import com.example.data.core.Either
import com.example.data.core.Failure
import com.example.domain.entity.CurrencyHistory
import com.example.domain.repository.CurrencyRepository
import com.example.domain.repository.CurrencyRepository.Range

/**
 * Created by jsmirabal on 4/19/2019.
 */
class FetchCurrencyHistory(private val repository: CurrencyRepository): BaseUseCase<CurrencyHistory, Range>() {

    override suspend fun run(params: Range): Either<Failure, CurrencyHistory> = repository.fetchHistory(params)
}