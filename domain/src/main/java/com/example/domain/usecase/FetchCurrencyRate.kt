package com.example.domain.usecase

import com.example.domain.entity.CurrencyRate
import com.example.domain.core.BaseUseCase
import com.example.domain.core.Either
import com.example.domain.core.Failure
import com.example.domain.repository.CurrencyRateRepository
import com.example.domain.repository.CurrencyRateRepository.*
import javax.inject.Inject

/**
 * Created by jsmirabal on 4/19/2019.
 */
class FetchCurrencyRate
    @Inject constructor(private val repository: CurrencyRateRepository): BaseUseCase<CurrencyRate, Range>() {

    override suspend fun run(params: Range): Either<Failure, CurrencyRate> = repository.fetchRange(params)
}