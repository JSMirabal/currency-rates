package com.example.data.repository

import com.example.data.BaseUnitTest
import com.example.domain.core.Failure
import com.example.domain.core.Params
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 * Created by jsmirabal on 4/21/2019.
 */
class CurrencyRepositoryImplTest : BaseUnitTest() {

    private val startDate = "04-01-2019"
    private val endDate = "04-21-2019"

    /*@Test
    fun `executing fetchRange should return anything`() {
        val result = CurrencyRepositoryImpl().fetchHistory(Params(startDate, endDate))
        println(result)
        result shouldEqual endDate
    }*/

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