package com.example.data.db

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.BaseAndroidTest
import com.example.data.db.dao.CurrencyHistoryDao
import com.example.data.db.entity.CurrencyEntity
import com.example.data.db.entity.HistoryEntity
import com.example.data.db.entity.RateEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class DatabaseTest: BaseAndroidTest() {
    private lateinit var historyDao: CurrencyHistoryDao
    private lateinit var db: CurrencyDatabase
    private val currencyHistory = HistoryEntity(
        0, "USD", "2019-04-01", "2019-04-26"
    )
    private val historyRates = listOf(
        RateEntity(date = "2019-04-03"),
        RateEntity(date = "2019-04-05"),
        RateEntity(date = "2019-04-08"),
        RateEntity(date = "2019-04-10")
    )

    private val rateCurrencies = listOf(
        CurrencyEntity(name = "EUR", value = 0.1234),
        CurrencyEntity(name = "GBP", value = 0.0234)
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
        Log.e("lala", history.toString())
    }
}