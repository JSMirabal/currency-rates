package usecase


import com.example.domain.core.Either
import com.example.domain.core.Either.Left
import com.example.domain.core.Either.Right
import com.example.domain.core.Failure
import com.example.domain.core.Params
import com.example.domain.entity.CurrencyHistory
import com.example.domain.repository.CurrencyRepository
import com.example.domain.usecase.FetchCurrencyHistory
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Created by jsmirabal on 4/21/2019.
 */
@ExtendWith(MockKExtension::class)
class FetchCurrencyHistoryTest {

    private val repository = mockk<CurrencyRepository>()
    private val fetchCurrencyRate: FetchCurrencyHistory = FetchCurrencyHistory(repository)
    private val startDate = "04-01-2019"
    private val endDate = "04-21-2019"
    private val params = Params(startDate, endDate)
    private val currencyHistory = CurrencyHistory(params.base, emptyList(), params.startDate, params.endDate)

    @BeforeEach
    fun before() {
        every { repository.fetchHistory(params) } returns Right(currencyHistory)
    }

    @AfterEach
    fun after() {
        verify { repository.fetchHistory(params) }
        confirmVerified(repository)
    }

    @Test
    fun `running use case should return expected Right value`() {
        val result = runBlocking { fetchCurrencyRate.run(params) }
        result shouldEqual Right(currencyHistory)
    }

    @Test
    fun `running use case should return expected Left value`() {
        val expected = Left(Failure.ApiFailure("Something went wrong"))
        every { repository.fetchHistory(params) } returns expected
        val result = runBlocking { fetchCurrencyRate.run(params) }
        result shouldEqual expected
    }


    @Test
    fun `running async use case should return expected Right value`() {
        var result: Either<Failure, CurrencyHistory>? = null
        runBlocking { result = fetchCurrencyRate(params).await() }
        result shouldEqual Right(currencyHistory)
    }

    @Test
    fun `running async use case should return expected Left value`() {
        var result: Either<Failure, CurrencyHistory>? = null
        val expected: Either<Failure, CurrencyHistory> = Left(Failure.ApiFailure("Something went wrong"))
        every { repository.fetchHistory(params) } returns expected
        runBlocking { result = fetchCurrencyRate(params).await() }
        result shouldEqual expected
    }

    @Test
    fun `calling cancel() should return a job cancelled`() {
        every { repository.fetchHistory(params) } answers {
            Thread.sleep(1000)
            Right(currencyHistory)
        }
        runBlocking {
            val job = fetchCurrencyRate(params)
            fetchCurrencyRate.cancel()
            (job.isCancelled) shouldBe true
        }
    }
}