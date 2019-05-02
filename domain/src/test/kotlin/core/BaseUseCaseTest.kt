package core

import com.example.domain.core.BaseUseCase
import com.example.domain.core.Either
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Created by jsmirabal on 4/19/2019.
 */
@ExperimentalCoroutinesApi
@ExtendWith(TestDispatcherExtension::class)
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
        val params = MyParams("TestParam")

        myUseCase(params) { result ->
            result shouldEqual Either.Right(MyType(TYPE_TEST))
        }
    }

    @Test
    fun `calling cancel() should return a job cancelled`() {
        myUseCase.delay = 3000
        val params = MyParams("TestParam")
        myUseCase(params)
        myUseCase.cancel()
        myUseCase.isCancelled() shouldBe true
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