package com.example.domain.usecase

import com.example.domain.core.BaseUseCase
import com.example.domain.core.Either
import com.example.domain.core.Failure
import com.example.domain.entity.CurrencyRate
import com.example.domain.repository.CurrencyRepository
import com.example.domain.repository.CurrencyRepository.Range
import javax.inject.Inject

/**
 * Created by jsmirabal on 4/19/2019.
 */
class FetchCurrencyRate
    @Inject constructor(private val repository: CurrencyRepository): BaseUseCase<CurrencyRate, Range>() {

    override suspend fun run(params: Range): Either<Failure, CurrencyRate> = repository.fetchRange(params)
}