package core

import com.example.domain.core.BaseUseCase
import com.example.domain.core.Either
import com.example.domain.core.Failure
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * Created by jsmirabal on 4/19/2019.
 */
class BaseUseCaseTest {

    private val myUseCase = MyUseCase()

    @Test
    fun `running use case should return expected Right value`() {
        val params = MyParams(TYPE_PARAM)
        val result = runBlocking { myUseCase.run(params) }

        result shouldEqual Either.Right(MyType(TYPE_TEST))
    }

    @Test
    fun `running async use case should return expected Right value`() {
        var result: Either<Failure, MyType>? = null

        val params = MyParams("TestParam")

        runBlocking { result = myUseCase(params).await() }

        result shouldEqual Either.Right(MyType(TYPE_TEST))
    }

    @Test
    fun `calling cancel() should return a job cancelled`() {
        runBlocking {
            myUseCase.delay = 3000
            val params = MyParams("TestParam")
            val job = myUseCase(params)
            myUseCase.cancel()
            job.isCancelled shouldBe true
        }
    }
}

private val TYPE_TEST = "Test"
private val TYPE_PARAM = "ParamTest"

private data class MyType(val name: String)
private data class MyParams(val name: String)

private class MyUseCase(var delay: Long = 0) : BaseUseCase<MyType, MyParams>() {
    override suspend fun run(params: MyParams) = runBlocking {
        delay(delay)
        Either.Right(MyType(TYPE_TEST))
    }
}