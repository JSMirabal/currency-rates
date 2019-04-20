import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by jsmirabal on 4/19/2019.
 */
@RunWith(MockitoJUnitRunner::class)
abstract class BaseUnitTest {

    @Suppress("LeakingThis")
    @Rule
    @JvmField val injectMocks = create(this)

    private fun create(testClass: Any) = TestRule { base, _ -> base }.apply { MockitoAnnotations.initMocks(testClass) }

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("Test")

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}