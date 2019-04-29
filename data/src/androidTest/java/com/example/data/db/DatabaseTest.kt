package com.example.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.BaseAndroidTest
import com.example.data.db.dao.CurrencyHistoryDao
import com.example.data.db.entity.CurrencyEntity
import com.example.data.db.entity.HistoryEntity
import com.example.data.db.entity.RateEntity
import com.example.data.network.HistoryResponse
import com.example.data.network.HistoryResponse.Rate
import com.example.data.network.HistoryResponse.Rate.Currency
import org.amshove.kluent.shouldEqual
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class DatabaseTest: BaseAndroidTest() {
    private lateinit var historyDao: CurrencyHistoryDao
    private lateinit var db: CurrencyDatabase
    private val currencyHistory = HistoryEntity(
        4444, "USD", "2019-04-01", "2019-04-26"
    )
    private val historyRates = listOf(
        RateEntity(1, date = "2019-04-03", historyId = 4444),
        RateEntity(2, date = "2019-04-05", historyId = 4444),
        RateEntity(3, date = "2019-04-08", historyId = 4444),
        RateEntity(4, date = "2019-04-10", historyId = 4444)
    )

    private val rateCurrencies = listOf(
        CurrencyEntity(1, name = "EUR", value = 0.1234, rateId = 1),
        CurrencyEntity(2, name = "GBP", value = 0.0234, rateId = 1),
        CurrencyEntity(3, name = "EUR", value = 0.1234, rateId = 2),
        CurrencyEntity(4, name = "GBP", value = 0.0234, rateId = 2),
        CurrencyEntity(5, name = "EUR", value = 0.1234, rateId = 3),
        CurrencyEntity(6, name = "GBP", value = 0.0234, rateId = 3),
        CurrencyEntity(7, name = "EUR", value = 0.1234, rateId = 4),
        CurrencyEntity(8, name = "GBP", value = 0.0234, rateId = 4)
    )

    private val historyResponse = HistoryResponse(
        base = "EUR", startDate = "2019-04-01", endDate = "2019-04-26", rates = listOf(
            Rate(
                date = "2019-04-03", currencies = listOf(
                    Currency(name = "USD", value = 1.2421),
                    Currency(name = "GBP", value = 0.8142)
                )
            ),
            Rate(
                date = "2019-04-05", currencies = listOf(
                    Currency(name = "USD", value = 1.3421),
                    Currency(name = "GBP", value = 0.9142)
                )
            )
        )
    )

    @Before
    override fun before() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, CurrencyDatabase::class.java).build()
        historyDao = db.historyDao()
    }

    @After
    @Throws(IOException::class)
    override fun after() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeHistoryAndReadItsValues() {
        historyDao.insertCurrencyHistory(currencyHistory)
        val history = historyDao.loadHistory()
        history shouldEqual currencyHistory
    }

    @Test
    @Throws(Exception::class)
    fun writeRatesAndReadItsValues() {
        historyDao.insertCurrencyHistory(currencyHistory)
        historyDao.insertRates(historyRates)
        val rates = historyDao.loadHistoryRates()
        rates shouldEqual historyRates
    }

    @Test
    @Throws(Exception::class)
    fun writeCurrenciesAndReadItsValues() {
        historyDao.insertCurrencyHistory(currencyHistory)
        historyDao.insertRates(historyRates)
        historyDao.insertCurrencies(rateCurrencies)
        val currencies = historyDao.loadRateCurrencies()
        currencies shouldEqual rateCurrencies
    }

    @Test
    @Throws(Exception::class)
    fun readFullHistory() {
        historyDao.insertCurrencyHistory(currencyHistory)
        historyDao.insertRates(historyRates)
        historyDao.insertCurrencies(rateCurrencies)
        val history = historyDao.loadHistoryCache()
        history.history shouldEqual currencyHistory
    }

    @Test
    @Throws(Exception::class)
    fun insertHistoryResponse_LoadingDataShouldReturnSameValues() {
        historyDao.insert(historyResponse)
        val result = historyDao.loadHistoryCache()
        result.rates?.size shouldEqual historyResponse.rates.size
    }
}