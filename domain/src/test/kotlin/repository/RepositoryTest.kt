package repository

import BaseUnitTest
import com.example.data.core.Failure.ApiFailure
import com.example.data.core.Failure.NetworkFailure
import com.example.domain.repository.CurrencyRepository
import com.example.domain.repository.CurrencyRepository.Range
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 * Created by jsmirabal on 4/21/2019.
 */
class RepositoryTest: BaseUnitTest() {

    private val startDate = "04-01-2019"
    private val endDate = "04-21-2019"

    @Test
    fun `executing fetchRange() should return Failure or CurrencyRate`() {

        val params = Range(startDate, endDate)
        val result = runBlocking { CurrencyRepository().fetchHistory(params) }

        result.either(
            { failure ->
                (failure is NetworkFailure || failure is ApiFailure) shouldEqual true
            },
            { success ->
                params.startDate shouldEqual success.startDate
                params.endDate shouldEqual success.endDate
            })
    }

}