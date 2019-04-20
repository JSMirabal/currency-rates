import com.example.domain.core.BaseUseCase
import com.example.domain.core.Either
import com.example.domain.core.Failure
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 * Created by jsmirabal on 4/19/2019.
 */
class BaseUseCaseTest : BaseUnitTest() {

    private val useCase = MyUseCase()

    @Test
    fun `_run should return 'Either' of the same value_`() {
        val params = MyParams(TYPE_PARAM)
        val result = runBlocking { useCase.run(params) }

        result shouldEqual Either.Right(MyType(TYPE_TEST))
    }

    @Test
    fun `_execute should return 'Either' of the same value_`() {
        var result: Either<Failure, MyType>? = null

        val params = MyParams("TestParam")

        runBlocking { result = useCase.executeAsync(params).await() }

        result shouldEqual Either.Right(MyType(TYPE_TEST))
    }

    @Test
    fun `_after execute should cancel or complete_`() {
        runBlocking {
            val params = MyParams("TestParam")
            val job = useCase.executeAsync(params)
            useCase.cancel()
            (job.isCancelled || job.isCompleted) shouldBe true
        }
    }
}

private val TYPE_TEST = "Test"
private val TYPE_PARAM = "ParamTest"

private data class MyType(val name: String)
private data class MyParams(val name: String)

private class MyUseCase : BaseUseCase<MyType, MyParams>() {
    override suspend fun run(params: MyParams) = Either.Right(MyType(TYPE_TEST))
}