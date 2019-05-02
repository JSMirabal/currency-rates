package core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import java.util.concurrent.Executors

/**
 * Created by jsmirabal on 5/1/2019.
 */

@ExperimentalCoroutinesApi
class TestDispatcherExtension: BeforeAllCallback, AfterAllCallback {
    private val singleThreadExecutor = Executors.newSingleThreadExecutor()

    override fun beforeAll(context: ExtensionContext?) {
        Dispatchers.setMain(singleThreadExecutor.asCoroutineDispatcher())
    }

    override fun afterAll(context: ExtensionContext?) {
        singleThreadExecutor.shutdownNow()
        Dispatchers.resetMain()
    }
}