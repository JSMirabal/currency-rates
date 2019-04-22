import com.example.data.Provider
import org.junit.Test

/**
 * Created by jsmirabal on 4/21/2019.
 */
class ProviderTest: BaseUnitTest() {

    private val startDate = "04-01-2019"
    private val endDate = "04-21-2019"

    @Test
    fun `executing fetchRange should return anything`() {
        val result = Provider.fetchHistory(startDate, endDate)
        println(result)
    }
}