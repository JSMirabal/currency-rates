package com.example.data.repository

import com.example.data.network.Service
import com.example.domain.core.Params
import org.junit.jupiter.api.Test

/**
 * Created by jsmirabal on 4/21/2019.
 */
class CurrencyRepositoryImplTest {

    private val startDate = "2019-04-01"
    private val endDate = "2019-04-21"

    @Test
    fun `executing fetchRange should return anything`() {
        val result = Service.fetchHistory(Params(startDate, endDate))
        println(result)
    }

    /*@Test
    fun `executing fetchRange() should return Failure or CurrencyHistory`() {

        val params = Params(startDate, endDate)
        val result = runBlocking { CurrencyRepositoryImpl().fetchHistory(params) }

        result.either(
            { failure ->
                (failure is Failure.NetworkFailure || failure is Failure.ApiFailure) shouldEqual true
            },
            { success ->
                params.startDate shouldEqual success.startDate
                params.endDate shouldEqual success.endDate
            })
    }*/
}