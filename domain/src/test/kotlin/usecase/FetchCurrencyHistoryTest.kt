package usecase

import BaseUnitTest
import com.example.data.core.Either
import com.example.data.core.Failure
import com.example.data.core.Failure.ApiFailure
import com.example.data.core.Failure.NetworkFailure
import com.example.domain.entity.CurrencyHistory
import com.example.domain.repository.CurrencyRepository
import com.example.domain.repository.CurrencyRepository.Range
import com.example.domain.usecase.FetchCurrencyHistory
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotBe
import org.junit.Test

/**
 * Created by jsmirabal on 4/21/2019.
 */
class FetchCurrencyHistoryTest: BaseUnitTest() {

    private val fetchCurrencyRate = FetchCurrencyHistory(CurrencyRepository())
    private val startDate = "04-01-2019"
    private val endDate = "04-21-2019"

    @Test
    fun `running use case should return Failure or CurrencyHistory`() {

        val params = Range(startDate, endDate)
        val result = runBlocking { fetchCurrencyRate.run(params) }

        result.either(
            { failure ->
                (failure is NetworkFailure || failure is ApiFailure) shouldEqual true
            },
            { success ->
                params.startDate shouldEqual success.startDate
                params.endDate shouldEqual success.endDate
            })
    }

    @Test
    fun `running async use case should return Failure or CurrencyRate`() {
        var result: Either<Failure, CurrencyHistory>? = null

        val params = Range(startDate, endDate)

        runBlocking { result = fetchCurrencyRate(params).await() }

        result shouldNotBe null

        result?.either(
            { failure ->
                (failure is NetworkFailure || failure is ApiFailure) shouldEqual true
            },
            { success ->
                params.startDate shouldEqual success.startDate
                params.endDate shouldEqual success.endDate
            })
    }

    @Test
    fun `calling cancel() should return a job cancelled`() {
        runBlocking {
            val params = Range(startDate, endDate)
            val job = fetchCurrencyRate(params)
            fetchCurrencyRate.cancel()
            (job.isCancelled || job.isCompleted) shouldBe true
        }
    }
}